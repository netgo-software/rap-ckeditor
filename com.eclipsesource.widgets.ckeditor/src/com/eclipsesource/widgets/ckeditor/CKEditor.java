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
  private String text = "";
  Browser browser;
  boolean loaded = false;
  boolean ready = false;
  
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
    this.ready = false;
    if( loaded ) {
      browser.evaluate( getCodeSetText() );          
    }
  }

  public String getText() {
    String result;
    if( ready ) {
      result = ( String )browser.evaluate( getCodeGetText() );
    } else {
      result = text;
    }
    return result;
  }

  public void applyStyle( Style style ) {
    browser.evaluate( getCodeApplyStyle( style ) );
  }

  public void removeFormat() {
    browser.evaluate( getCodeRemoveFormat() );
  }

  ///////////////////////////
  // browser function handler
  
  void onReady() {
    if( !loaded ) {
      loaded = true;
      syncAll();
    }
    ready = true;
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
      browser.evaluate( getCodeSetText() );
    }
  }

  private String getCodeSetText() {
    return "rap.editor.setData( \"" + escapeText( text ) + "\" );";
  }
  
  private String getCodeGetText() {
    return "return rap.editor.getData();";
  }
  
  private String getCodeRemoveFormat() {
    return "rap.editor.execCommand( \"removeFormat\" );";
  }

  private String getCodeApplyStyle( Style style ) {
    StringBuilder code = new StringBuilder();
    code.append( "var style = new CKEDITOR.style( " );
    code.append( style.toJSON() );
    code.append( " );style.apply( rap.editor.document );" );
    return code.toString();
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
