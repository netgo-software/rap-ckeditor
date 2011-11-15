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

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;


public class CKEditor extends Composite {

  private static final String URL = "/resources/ckeditor.html";
  private final static String READY_FUNCTION = "rap_ready";
  Browser browser;
  private boolean loaded = false;

  public CKEditor( Composite parent, int style ) {
    super( parent, style );
    super.setLayout( new FillLayout() );
    browser = new Browser( this, style );
    browser.setUrl( URL );
    createBrowserFunctions();
  }
  
  //////////////////////////////
  // overwrite composite methods

  @Override
  public void setLayout( Layout layout ) {
    throw new UnsupportedOperationException( "Cannot change internal layout of CkEditor" );
  }
  
  @Override
  public Control[] getChildren() {
    return new Control[ 0 ];
  }
  
  //////
  // API
  
  ///////////////
  // internal API
  
  boolean isLoaded() {
    return loaded;
  }
  
  ///////////////////////////
  // browser function handler
  
  void onReady() {
    loaded = true;
  }
  
  private void createBrowserFunctions() {
    new BrowserFunction( browser, READY_FUNCTION ) {
      public Object function( Object[] arguments ) {
        return null;
      }
    };
  }

}
