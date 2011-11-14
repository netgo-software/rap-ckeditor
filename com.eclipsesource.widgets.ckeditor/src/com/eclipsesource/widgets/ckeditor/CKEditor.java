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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;


public class CKEditor extends Composite {

  private static final String URL = "/resources/ckeditor.html";
  private final static String SAVE_FUNCTION = "download";
  private boolean loaded;
  private Browser browser;

  public CKEditor( Composite parent, int style ) {
    this( parent, style, null );
  }

  CKEditor( Composite parent, int style, Browser testBrowser ) {
    super( parent, style );
    super.setLayout( new FillLayout() );
    browser = testBrowser != null ? testBrowser : new Browser( this, SWT.NONE );
    browser.setUrl( URL );
    browser.addProgressListener( new ProgressListener() {

      public void completed( ProgressEvent event ) {
        loaded = true;
        createBrowserFunctions();
      }

      public void changed( ProgressEvent event ) {
        // not needed
      }
    } );
  }

  @Override
  public void setLayout( Layout layout ) {
    throw new UnsupportedOperationException( "Cannot change internal layout of CkEditor" );
  }

  /**
   * Reads editor's current input and provides it as download.
   */
  public void save() {
    if( loaded ) {
      browser.evaluate( "readContent();" );
    } else {
      MessageDialog.openWarning( getShell(),
                                 "Still Loading",
                                 "Save not possible. Still loading." );
    }
  }

  private void createBrowserFunctions() {
    new BrowserFunction( browser, SAVE_FUNCTION ) {

      @Override
      public Object function( Object[] arguments ) {
        String editorContent = ( String )arguments[ 0 ];
        return null;
      }
    };
  }

}
