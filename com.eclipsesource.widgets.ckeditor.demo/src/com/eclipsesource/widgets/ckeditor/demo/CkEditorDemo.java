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
package com.eclipsesource.widgets.ckeditor.demo;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.eclipsesource.widgets.ckeditor.CKEditor;


public class CkEditorDemo implements IEntryPoint {

  public int createUI() {
    Display display = new Display();
    Shell shell = new Shell( display );
    shell.setBounds( 10, 10, 800, 550 );
    shell.setText( "CkEditor Demo" );
    shell.setLayout( new GridLayout() );
    // CkEditor
    final CKEditor ckEditor = new CKEditor( shell, SWT.BORDER );
    ckEditor.setText( "bala<i>\"la\\la\"</i>la\r\nfoooo" );
    //ckEditor.setBackground( display.getSystemColor( SWT.COLOR_BLUE ) );
    GridDataFactory.fillDefaults().grab( true, true ).applyTo( ckEditor );
    // Save button
    Button downloadBtn = new Button( shell, SWT.NONE );
    downloadBtn.setText( "Save" );
    downloadBtn.setLayoutData( new GridData( SWT.RIGHT, GridData.CENTER, false, false ) );
    downloadBtn.addSelectionListener( new SelectionAdapter() {

      @Override
      public void widgetSelected( SelectionEvent e ) {
      }
    } );
    shell.open();
    runReadAndDispatchLoop( shell );
    return 0;
  }

  private void runReadAndDispatchLoop( Shell shell ) {
    while( !shell.isDisposed() ) {
      if( !shell.getDisplay().readAndDispatch() ) {
        shell.getDisplay().sleep();
      }
    }
  }
}
