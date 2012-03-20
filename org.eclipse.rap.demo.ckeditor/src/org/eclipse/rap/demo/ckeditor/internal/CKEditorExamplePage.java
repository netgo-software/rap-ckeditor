/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.demo.ckeditor.internal;

import org.eclipse.rap.examples.ExampleUtil;
import org.eclipse.rap.examples.IExamplePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.eclipsesource.widgets.ckeditor.CKEditor;


public class CKEditorExamplePage implements IExamplePage {
  
  public void createControl( Composite parent ) {
    parent.setLayout( ExampleUtil.createMainLayout( 2 ) );
    createEditorExample( parent );
  }

  private void createEditorExample( Composite parent ) {
    final Display display = parent.getDisplay();
    Composite composite = new Composite( parent, SWT.NONE );
    composite.setLayoutData( ExampleUtil.createFillData() );
    composite.setLayout( ExampleUtil.createGridLayout( 1, true, true, true ) );
    final CKEditor ckEditor = new CKEditor( composite, SWT.NONE );
    ckEditor.setText( "<b>Rich</b> <i>Text</i> <u>Editor</u>" );
    GridData layoutData = new GridData( SWT.FILL, SWT.FILL, true, true  );
    ckEditor.setLayoutData( layoutData );
    ckEditor.setBackground( new Color( display, 247, 247, 247 ) );
    Composite toolbar = new Composite( composite, SWT.NONE );
    toolbar.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, false  ) );
    toolbar.setLayout( new GridLayout( 3, false ) );
    Button printBtn = new Button( toolbar, SWT.PUSH );
    printBtn.setText( "Show Content" );
    printBtn.addSelectionListener( new SelectionAdapter() {
      public void widgetSelected( SelectionEvent e ) {
        //System.out.println( ckEditor.getText() );
      }
    } );
    Button fontBtn = new Button( toolbar, SWT.PUSH );
    fontBtn.setText( "Font" );
    fontBtn.addSelectionListener( new SelectionAdapter() {
      public void widgetSelected( SelectionEvent e ) {
        ckEditor.setFont( new org.eclipse.swt.graphics.Font( display, "serif", 13, 0 ) );
      }
    } );
    Button clearBtn = new Button( toolbar, SWT.NONE );
    clearBtn.setText( "Clear" );
    clearBtn.addSelectionListener( new SelectionAdapter() {
      public void widgetSelected( SelectionEvent e ) {
        ckEditor.setText( "" );
      }
    } );
    
  }

}
