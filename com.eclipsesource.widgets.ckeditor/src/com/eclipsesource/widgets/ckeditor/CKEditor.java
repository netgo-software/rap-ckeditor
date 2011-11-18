/*******************************************************************************
 * Copyright (c) 2011 EclipseSource
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.widgets.ckeditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
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
  boolean clientReady = false;
  private StringBuilder scriptBuffer = null;

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

  @Override
  public void setFont( Font font ) {
    super.setFont( font );
    writeFont();
  }

  //////
  // API

  public void setText( String text ) {
    checkWidget();
    if( text == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    this.text = text;
    writeText();          
    clientReady = false; // order is important
  }

  public String getText() {
    checkWidget();
    readText();
    return text;
  }

  //////////////
  // browser I/O

  void onReady() {
    writeFont(); // CKEditor re-creates the document with every setData, losing inline styles
    evalOnReadyScript();
    clientReady = true;
  }

  private void writeText() {
    evalOnReady( "rap.editor.setData( \"" + escapeText( text ) + "\" );" );
  }

  private void readText() {
    if( clientReady ) {
      text = ( String )browser.evaluate( "return rap.editor.getData();" );
    }
  }

  /////////
  // helper

  private void addBrowserHandler() {
    new BrowserFunction( browser, READY_FUNCTION ) {
      public Object function( Object[] arguments ) {
        onReady();
        return null;
      }
    };
  }

  private void evalOnReady( String script ) {
    if( clientReady ) {
      browser.evaluate( script );
    } else {
      if( scriptBuffer == null ) {
        scriptBuffer = new StringBuilder();
      }
      scriptBuffer.append( script );
    }
  }

  private void evalOnReadyScript() {
    if( scriptBuffer != null ) {
      browser.evaluate( scriptBuffer.toString() );
      scriptBuffer = null;
    }
  }

  private void writeFont() {
    evalOnReady( "rap.editor.document.getBody().setStyle( \"font\", \"" + getCssFont() + "\" );" );
  }

  private String getCssFont() {
    StringBuilder result = new StringBuilder();
    if( getFont() != null ) {
      FontData data = getFont().getFontData()[ 0 ];
      result.append( data.getHeight() );
      result.append( "px " );
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