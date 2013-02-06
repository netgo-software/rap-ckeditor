package com.eclipsesource.widgets.ckeditor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.rap.rwt.testfixture.Fixture;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class CKEditor_Test extends TestCase {

  private Display display;
  private Shell shell;
  private CKEditor editor;
  private Connection connection;
  private RemoteObject remoteObject;

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
    display = new Display();
    shell = new Shell( display );
    Fixture.fakeNewRequest();
    remoteObject = mock( RemoteObject.class );
    connection = mock( Connection.class );
    when( connection.createRemoteObject( anyString() ) ).thenReturn( remoteObject );
    Fixture.fakeConnection( connection );
    editor = new CKEditor( shell, SWT.BORDER );
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void testSetLayout() {
    try {
      editor.setLayout( new FillLayout() );
      fail();
    } catch( UnsupportedOperationException ex ) {
      // expected
    }
  }

  public void testContructor_CreatesRemoteObjectWithCorrectType() {
    verify( connection ).createRemoteObject( eq( "eclipsesource.CKEditor" ) );
  }

  public void testContructor_LoadsJavaScriptFiles() {
    JavaScriptLoader loader = mockJavaScriptLoader();
    ResourceManager resourceManager = RWT.getResourceManager();

    new CKEditor( shell, SWT.BORDER );

    verify( loader ).require( resourceManager.getLocation( "ckeditor/ckeditor.js" ) );
    verify( loader ).require( resourceManager.getLocation( "ckeditor/config.js" ) );
    verify( loader ).require( resourceManager.getLocation( "ckeditor/handler.js" ) );
  }

  public void testSetText() {
    String text = "foo<span>bar</span>";

    editor.setText( text );

    verify( remoteObject ).set( "text", text );
  }

  private JavaScriptLoader mockJavaScriptLoader() {
    WebClient client = mock( WebClient.class );
    Fixture.fakeClient( client );
    JavaScriptLoader loader = mock( JavaScriptLoader.class );
    when( client.getService( JavaScriptLoader.class ) ).thenReturn( loader );
    return loader;
  }

  public void testSetTextNull() {
    try {
      editor.setText( null );
      fail();
    } catch( IllegalArgumentException ex ) {
      // expected
    }
  }


//
//  public void testSetFontAfterReady() {
//    mockBrowser( editor );
//
//    editor.onReady();
//
//    String expected = "setStyle( \"font\"";
//    verify( editor.browser, times( 1 ) ).evaluate( contains( expected ) );
//  }

//  public void testSetFontFamilyAndSize() {
//    mockBrowser( editor );
//    editor.onReady();
//
//    editor.setFont( new Font( display, "fantasy", 13, 0 ) );
//
//    String expected = "setStyle( \"font\", \"13px fantasy";
//    verify( editor.browser, times( 1 ) ).evaluate( contains( expected ) );
//  }

//  public void testSetFontEscape() {
//    mockBrowser( editor );
//    editor.onReady();
//
//    editor.setFont( new Font( display, "\"courier new\"", 13, 0 ) );
//
//    String expected = "setStyle( \"font\", \"13px \\\"courier new\\\"";
//    verify( editor.browser, times( 1 ) ).evaluate( contains( expected ) );
//  }

  /////////
  // Helper


}
