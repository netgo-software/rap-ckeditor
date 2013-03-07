/*******************************************************************************
 * Copyright (c) 2002, 2012 Innoopract Informationssysteme GmbH and others.
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
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.eclipsesource.widgets.ckeditor.CKEditor;


public class CkEditorDemo implements EntryPoint {

  public int createUI() {
    final Display display = new Display();
    Shell shell = new Shell( display );
    shell.setBounds( 10, 10, 800, 550 );
    shell.setText( "CkEditor Demo" );

    shell.setLayout( new GridLayout( 1, false ) );
    // CkEditor
    final CKEditor ckEditor = new CKEditor( shell, SWT.NONE );
    ckEditor.setFont( new org.eclipse.swt.graphics.Font( display, "fantasy", 19, 0 ) );
    ckEditor.setText( "Hello Fantasy Font" );
    ckEditor.setLayoutData( new GridData() );
    System.out.println( ckEditor.getText() );
    ckEditor.setBackground( display.getSystemColor( SWT.COLOR_YELLOW ) );
    GridDataFactory.fillDefaults().grab( true, true ).applyTo( ckEditor );
    ToolBar toolbar = new ToolBar( shell, SWT.FLAT );
    ToolItem printBtn = new ToolItem( toolbar, SWT.PUSH );
    printBtn.setText( "Print" );
    printBtn.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        System.out.println( ckEditor.getText() );
      }
    } );
    ToolItem destrBtn = new ToolItem( toolbar, SWT.PUSH );
    destrBtn.setText( "Dispose" );
    destrBtn.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        ckEditor.dispose();
      }
    } );
    ToolItem fontBtn = new ToolItem( toolbar, SWT.PUSH );
    fontBtn.setText( "Font" );
    fontBtn.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        ckEditor.setFont( new org.eclipse.swt.graphics.Font( display, "serif", 9, 0 ) );
      }
    } );
    ToolItem clearBtn = new ToolItem( toolbar, SWT.NONE );
    clearBtn.setText( "Clear" );
    clearBtn.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        ckEditor.setText( "" );
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
