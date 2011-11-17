/*******************************************************************************
 * Copyright (c) 2002, 2011 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package com.eclipsesource.widgets.ckeditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;



public class CKEditor extends Composite {

  private static final String URL = "/resources/ckeditor.html";
  private static final String READY_FUNCTION = "rap_ready";
  private String text = "";
  Browser browser;
  boolean ready = false;
  private StringBuilder evalScript = null;

  public CKEditor( Composite parent, int style ) {
    super( parent, style );
    super.setLayout( new FillLayout() );
    this.setBackgroundMode( SWT.INHERIT_FORCE );
    browser = new Browser( this, SWT.BORDER );
    browser.setUrl( URL );
    addBrowserHandler();
  }

  ////////////////////
  // overwrite methods

  @Override
  public void setLayout( Layout layout ) {
    throw new UnsupportedOperationException( "Cannot change internal layout of CkEditor" );
  }

//  TODO [ tb ] : can not be overwritten until RAP bug 363844 is fixed
//  @Override
//  public Control[] getChildren() {
//    return new Control[ 0 ];
//  }

  @Override
  public void setFont( Font font ) {
    super.setFont( font );
    writeFont();
  }

  //////
  // API

  public void setText( String text ) {
    if( text == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    this.text = text;
    evaluate( getScriptSetText() );          
    ready = false;
  }

  public String getText() {
    readText();
    return text;
  }

  //////////////
  // browser I/O

  void onLoad() {
    browser.evaluate( "rap.createEditor();" );
  }

  void onReady() {
    writeFont(); // CKEditor re-creates the document with every setData, loosing inline styles
    if( evalScript != null ) {
      browser.evaluate( evalScript.toString() );
      evalScript = null;
    }
    ready = true;
  }

  private void readText() {
    if( ready ) {
      text = ( String )browser.evaluate( "return rap.editor.getData();" );
    }
  }

  private void evaluate( String script ) {
    if( ready ) {
      browser.evaluate( script );
    } else {
      if( evalScript == null ) {
        evalScript = new StringBuilder( script );
      } else {
        evalScript.append(  script );
      }
    }
  }

  /////////
  // helper

  private void addBrowserHandler() {
    browser.addProgressListener( new ProgressListener() {
      public void completed( ProgressEvent event ) {
        onLoad();
      }
      public void changed( ProgressEvent event ) {
      }
    } );
    new BrowserFunction( browser, READY_FUNCTION ) {
      public Object function( Object[] arguments ) {
        onReady();
        return null;
      }
    };
  }

  private String getScriptSetText() {
    return "rap.editor.setData( \"" + escapeText( text ) + "\" );";
  }

  private void writeFont() {
    evaluate( "rap.editor.document.getBody().setStyle( \"font\", \"" + getCssFont() + "\" );" );
  }

  private String getCssFont() {
    StringBuilder result = new StringBuilder();
    if( getFont() != null ) {
      FontData data = getFont().getFontData()[ 0 ];
      result.append( data.getHeight() );
      result.append( "pt " );
      result.append( escapeText( data.getName() ) );
    }
    return result.toString();
  }
  
  private static String escapeText( String text ) {
    // escaping backslashes, double-quotes, newlines, and carriage-return 
    StringBuilder result = new StringBuilder();
    for( int i = 0; i < text.length(); i++ ) {
      char ch = text.charAt( i );
      if( ch == '\n' ) {
        result.append( "\\n" );
      } else if( ch == '\r' ) {
        result.append( "\\r" );
      } else if( ch == '\\' ) {
        result.append( "\\\\" );
      } else if( ch == '"' ) {
        result.append( "\\\"" );
      } else {
        result.append( ch );
      }
    }
    return result.toString();
  }

}
