# CKEditor for RAP

This is a custom widget for the Rich Ajax Platform (RAP) that wraps the CKEditor, a web-based WYSIWYG/Rich-Text editor. To run it you need RAP 1.4M4 or later.

## API
Currently, the API consists of the two methods <code>getText</code> and <code>setText</code>. 
The text can be any valid HTML, but should be limited to the subset the editor can handle.

## Customization

The editor can be customized by editing the files in the <code>src/resources</code> folder of the <code>com.eclipsesource.widgets.ckeditor</code> bundle.
You might need to clear the browsers chache and restart the server for all changes to take effect.

### Editor Configuration

Editing the file <code>config.js</code> lets you change the toolbar, language, and formatting options (fonts, colors).
Be careful, all changes here bear the risk of breaking the editor.

### Editor Theming

To change the icons, edit or replace <code>icons.png</code>.
To change the editors colors, borders, spacings, etc, edit <code>editor.css</code>. You can use a tool like Firebug to examine which CSS classes are used where in the editor.

### Advanced Customization

For various reasions some plugins have been removed from <code>ckeditor.js</code> and disabled in <code>config.js</code>, therefore not all options of the full CKEdtior are working.
If you wish, you can compile your own <code>ckeditor.js</code>. Get more info here: http://docs.cksource.com/CKEditor_3.x/Developers_Guide/CKPackager
The <code>ckeditor.pack</code> used for this project can be found in the bundles <code>docs</code> folder.

## Bundle overview

### com.eclipsesource.widgets.ckeditor

The Widget itself (<code>com.eclipsesource.widgets.ckeditor.CKEditor</code>) and the required resources.

### com.eclipsesource.widgets.ckeditor.demo

A demo application for the widget.
Contains a launch configuration.

### org.eclipse.rap.demo.ckeditor.

An addition to the RAP Examples Demo.

### com.eclipsesource.widgets.ckeditor.test

JUnit Tests.

## Legal

=== License ===

All classes are published under the terms of the Eclipse Public License v1.0