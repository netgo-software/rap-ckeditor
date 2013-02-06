/*******************************************************************************
 * Copyright (c) 2011, 2012 EclipseSource.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.widgets.ckeditor;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;


public class CKEditor extends Composite {

  private static final String RESOURCES_PATH = "resources/";
  private static final String REGISTER_PATH = "ckeditor/";

  private static final String[] RESOURCE_FILES = {
    "ckeditor.html",
    "ckeditor.js",
    "config.js",
    "contents.css",
    "lang/en.js",
    "skins/kama/editor.css",
    "skins/kama/icons.png",
    "skins/kama/images/sprites.png",
    "skins/kama/images/sprites_ie6.png"
  };
  private static final String REMOTE_TYPE = "eclipsesource.CKEditor";

  private String text = "";
  private RemoteObject remoteObject;

  public CKEditor( Composite parent, int style ) {
    super( parent, style );
    registerResources();
    loadJavaScript();
    Connection connection = RWT.getUISession().getConnection();
    remoteObject = connection.createRemoteObject( REMOTE_TYPE );
  }

  private void registerResources() {
    ResourceManager resourceManager = RWT.getResourceManager();
    boolean isRegistered = resourceManager.isRegistered( REGISTER_PATH + RESOURCE_FILES[ 0 ] );
    if( !isRegistered ) {
      try {
        for( String fileName : RESOURCE_FILES ) {
          register( resourceManager, fileName );
        }
      } catch( IOException ioe ) {
        throw new IllegalArgumentException( "Failed to load resources", ioe );
      }
    }
  }

  private void loadJavaScript() {
    JavaScriptLoader jsLoader = RWT.getClient().getService( JavaScriptLoader.class );
    ResourceManager resourceManager = RWT.getResourceManager();
    jsLoader.require( resourceManager.getLocation( REGISTER_PATH + "ckeditor.js" ) );
    jsLoader.require( resourceManager.getLocation( REGISTER_PATH + "config.js" ) );
    jsLoader.require( resourceManager.getLocation( REGISTER_PATH + "handler.js" ) );
  }

  private void register( ResourceManager resourceManager, String fileName ) throws IOException {
    ClassLoader classLoader = CKEditor.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream( RESOURCES_PATH + fileName );
    try {
      resourceManager.register( REGISTER_PATH + fileName, inputStream );
    } finally {
      inputStream.close();
    }
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
  }

  //////
  // API

  public void setText( String text ) {
    checkWidget();
    if( text == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    this.text = text;
    remoteObject.set( "text", text );
  }

  public String getText() {
    checkWidget();
    return text;
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
