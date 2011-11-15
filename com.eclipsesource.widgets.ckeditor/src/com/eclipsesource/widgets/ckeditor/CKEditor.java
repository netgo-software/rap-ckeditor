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
  private final static String READY_FUNCTION = "rap_ready";
  private Browser browser;
  private boolean loaded = false;

  public CKEditor( Composite parent, int style ) {
    this( parent, style, null );
  }

  CKEditor( Composite parent, int style, Browser testBrowser ) {
    super( parent, style );
    super.setLayout( new FillLayout() );
    browser = testBrowser != null ? testBrowser : new Browser( this, SWT.NONE );
    browser.setUrl( URL );
    createBrowserFunctions();
  }

  @Override
  public void setLayout( Layout layout ) {
    throw new UnsupportedOperationException( "Cannot change internal layout of CkEditor" );
  }
  
  boolean isLoaded() {
    return loaded;
  }
  
  ///////////////////////////
  // browser function handler
  
  void onReady( Object[] arguments ) {
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
