package com.eclipsesource.widgets.ckeditor;

import static org.mockito.Mockito.mock;
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
  
  public void testGetChildren() {
    assertEquals( 0, editor.getChildren().length );
  }

  public void testURL() {
    assertEquals( "/resources/ckeditor.html", editor.browser.getUrl() );
  }

  public void testIsInitiallyNotLoaded() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    assertFalse( editor.isLoaded() );
  }
  
  public void testIsLoadedOnReady() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    editor.onReady();
    assertTrue( editor.isLoaded() );
  }

  public void testSetLayout() {
    try {
      editor.setLayout( new FillLayout() );
      fail();
    } catch( UnsupportedOperationException ex ) {
      // expected
    }
  }

  /////////
  // Helper

  private void mockBrowser( CKEditor editor ) {
    Browser orgBrowser = editor.browser;
    editor.browser = mock( Browser.class );
    editor.browser.setUrl( orgBrowser.getUrl() );
  }

}
