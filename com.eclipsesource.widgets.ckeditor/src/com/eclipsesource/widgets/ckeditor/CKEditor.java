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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;



public class CKEditor extends Composite {

  private static final String URL = "/resources/ckeditor.html";
  private static final String READY_FUNCTION = "rap_ready";
  private static final String SETDATA_FUNCTION = "rap.editor.setData";
  private static final String GETDATA_FUNCTION = "rap.editor.getData";
  private String text = "";
  Browser browser;
  boolean loaded = false;
  
  public static final Style BOLD = new Style( "b" );
  public static final Style ITALIC = new Style( "i" );
  public static final Style UNDERLINE = new Style( "u" );
  public static final Style STRIKE = new Style( "strike" );
  public static final Style SUBSCRIPE = new Style( "sub" );
  public static final Style SUPERSCRIPE = new Style( "sup" );

  public CKEditor( Composite parent, int style ) {
    super( parent, style );
    super.setLayout( new FillLayout() );
    browser = new Browser( this, SWT.NONE );
    browser.setUrl( URL );
    createBrowserFunctions();
  }
  
  //////////////////////////////
  // overwrite composite methods

  @Override
  public void setLayout( Layout layout ) {
    throw new UnsupportedOperationException( "Cannot change internal layout of CkEditor" );
  }

//  TODO [ tb ] : can not be overwritten until RAP bug 363844 is fixed
//  @Override
//  public Control[] getChildren() {
//    return new Control[ 0 ];
//  }

  //////
  // API

  public void setText( String text ) {
    if( text == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    this.text = text;
    if( loaded ) {
      browser.evaluate( SETDATA_FUNCTION + "( \"" + escapeText( text ) + "\" );" );          
    }
  }

  public String getText() {
    String result;
    if( text != null ) {
      result = text;
    } else {
      result = ( String )browser.evaluate( "return " + GETDATA_FUNCTION + "();" );
    }
    return result;
  }

  public void applyStyle( Style style ) {
    StringBuilder code = new StringBuilder();
    code.append( "var style = new CKEDITOR.style( " );
    code.append( style.toJSON() );
    code.append( " );style.apply( rap.editor.document );" );
    browser.evaluate( code.toString() );
  }

  public void removeFormat() {
    browser.evaluate( "rap.editor.execCommand( \"removeFormat\" );" );
  }

  ///////////////////////////
  // browser function handler
  
  void onReady() {
    if( !loaded ) {
      loaded = true;
      syncAll();      
    }
    this.text = null;
  }
  
  ////////////
  // internals
  
  private void createBrowserFunctions() {
    new BrowserFunction( browser, READY_FUNCTION ) {
      public Object function( Object[] arguments ) {
        onReady();
        return null;
      }
    };
  }
  
  private void syncAll() {
    if( text != "" ) {
      browser.evaluate( SETDATA_FUNCTION + "( \"" + escapeText( text ) + "\" );" );
    }
  }
  
  // TODO [tb] : better implementation?
  private static String escapeText( String text ) {
    // escaping backslashes, double-quotes, newlines, and carriage-return 
    String result = text.replaceAll( "\\\\", "\\\\\\\\" );
    result = result.replaceAll( "\\\"", "\\\\\"" );
    result = result.replaceAll( "\\\n", "\\\\n" );
    result = result.replaceAll( "\\\r", "\\\\r" );
    return result;
  }

}
