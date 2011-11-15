/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config ) {

	config.language = 'en';
	
	config.skin = 'v2';

	config.removePlugins = [
	  "about",
	  "a11yhelp",
	  "basicstyles",
	  "bidi",
	  "blockquote",
	  "colorbutton",
	  "clipboard",
	  "colordialog",
	  "contextmenu", // perhaps later
	  "dialogadvtab",
	  "div",
	  "elementspath",
	  "filebrowser",
	  "find",
	  "flash",
	  "font",
	  "format",
	  "forms",
	  "horizontalrule",
	  "iframe","" +
	  "image", // perhaps later
	  "link", // perhaps later
	  "liststyle",
	  "newpage",
	  "pagebreak",
	  "pastefromword",
	  "pastetext",
	  "popup",
	  "preview",
	  "print", // perhaps later
	  "resize",
	  "save",
	  "scayt",
	  "smiley",
	  "showblocks", // perhaps later
	  "showborders",
	  "sourcearea",
	  "stylescombo",
	  "table",
	  "tabletools",
	  "specialchar",
	  "tab", // what might this be needed for?
	  "templates",
	  "undo", // perhaps later
	  "wsc",
	  "toolbar",
	  "panelbutton"
  ].join();

};
