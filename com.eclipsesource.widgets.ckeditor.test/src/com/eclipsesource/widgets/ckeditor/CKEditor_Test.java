package com.eclipsesource.widgets.ckeditor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import org.eclipse.rap.rwt.testfixture.Fixture;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CKEditor_Test extends TestCase {

  private Display display;
  private Shell shell;
  private CKEditor editor;

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
    display = new Display();
    shell = new Shell( display );
    editor = new CKEditor( shell, SWT.NONE );
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void testGetLayout() {
    assertTrue( editor.getLayout() instanceof FillLayout );
  }
  
//  public void testGetChildren() {
//    assertEquals( 0, editor.getChildren().length );
//  }

  public void testSetLayout() {
    try {
      editor.setLayout( new FillLayout() );
      fail();
    } catch( UnsupportedOperationException ex ) {
      // expected
    }
  }

  public void testURL() {
    assertEquals( "/resources/ckeditor.html", editor.browser.getUrl() );
  }

  public void testIsInitiallyNotLoaded() {
    assertFalse( editor.loaded );
  }
  
  public void testIsLoadedOnReady() {
    mockBrowser( editor );
    editor.onReady();
    assertTrue( editor.loaded );
  }

  public void testSetText() {
    mockBrowser( editor );
    editor.onReady();
    String text = "foo<span>bar</span>";
    
    editor.setText( text );
    
    String expected = "rap.editor.setData( \"" + text + "\" );";
    verify( editor.browser ).evaluate( expected );
  }

  public void testSetTextBeforeReady() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    mockBrowser( editor );
    String text = "foo<span>bar</span>";
    
    editor.setText( text );
    
    verify( editor.browser, times( 0 ) ).evaluate( anyString() );
  }
  
  public void testRenderTextAfterReady() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    mockBrowser( editor );
    String text = "foo<span>bar</span>";
    
    editor.setText( text );
    editor.onReady();
    
    String expected = "rap.editor.setData( \"" + text + "\" );";
    verify( editor.browser ).evaluate( contains( expected ) );
  }
  
  public void testDontRenderTextAfterSecondReady() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    mockBrowser( editor );
    editor.onReady();
    String text = "foo<span>bar</span>";
    
    editor.setText( text );
    editor.onReady();
    
    verify( editor.browser, times( 2 ) ).evaluate( contains( "setData" ) );
  }

  public void testSetTextEscape() {
    mockBrowser( editor );
    String text = "foo<span>\"bar\\</span>\r\n";

    editor.setText( text );
    editor.onReady();
    
    String expectedText = "foo<span>\\\"bar\\\\</span>\\r\\n";
    String expected = "rap.editor.setData( \"" + expectedText + "\" );";
    verify( editor.browser ).evaluate( expected );
  }

  /////////
  // Helper

  private void mockBrowser( CKEditor editor ) {
    Browser orgBrowser = editor.browser;
    editor.browser = mock( Browser.class );
    editor.browser.setUrl( orgBrowser.getUrl() );
  }

}
