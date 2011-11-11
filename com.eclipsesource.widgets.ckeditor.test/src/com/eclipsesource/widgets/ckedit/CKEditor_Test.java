package com.eclipsesource.widgets.ckedit;

import org.eclipse.rwt.lifecycle.PhaseId;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.rap.rwt.testfixture.Fixture;

import com.eclipsesource.widgets.ckeditor.CKEditor;

import junit.framework.TestCase;


public class CKEditor_Test extends TestCase {
  
  private Display display;
  private Shell shell;
  
  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
    Fixture.fakePhase( PhaseId.PROCESS_ACTION );
    display = new Display();
    shell = new Shell( display );
  }
  
  public void testCreateEditor() {
    CKEditor editor = new CKEditor( shell, SWT.NONE );
    assertTrue( editor instanceof CKEditor );
  }
  
}
