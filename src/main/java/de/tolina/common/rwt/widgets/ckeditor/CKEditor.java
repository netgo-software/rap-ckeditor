/*******************************************************************************
 * Copyright (c) 2011 EclipseSource.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 *    arxes-tolina  - change CKEditor to version 4
 ******************************************************************************/
package de.tolina.common.rwt.widgets.ckeditor;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rwt.RWT;
import org.eclipse.rwt.resources.IResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;


public class CKEditor extends Composite {

	private static final long serialVersionUID = -5001986574518550519L;
	private static final String RESOURCES_PATH = "resources/";
	private static final String REGISTER_PATH = "ckeditor/";
	private static final String READY_FUNCTION = "rap_ready";

	private static final String[] RESOURCE_FILES = { //
			"ckeditor.html", //
			"ckeditor.js", //
			"config.js", //
			"contents.css", //
			"styles.js", //
			"adapters/jquery.js", //
			"lang/af.js", //
			"lang/ar.js", //
			"lang/bg.js", //
			"lang/bn.js", //
			"lang/bs.js", //
			"lang/ca.js", //
			"lang/cs.js", //
			"lang/cy.js", //
			"lang/da.js", //
			"lang/de.js", //
			"lang/el.js", //
			"lang/en-au.js", //
			"lang/en-ca.js", //
			"lang/en-gb.js", //
			"lang/en.js", //
			"lang/eo.js", //
			"lang/es.js", //
			"lang/et.js", //
			"lang/eu.js", //
			"lang/fa.js", //
			"lang/fi.js", //
			"lang/fo.js", //
			"lang/fr-ca.js", //
			"lang/fr.js", //
			"lang/gl.js", //
			"lang/gu.js", //
			"lang/he.js", //
			"lang/hi.js", //
			"lang/hr.js", //
			"lang/hu.js", //
			"lang/id.js", //
			"lang/is.js", //
			"lang/it.js", //
			"lang/ja.js", //
			"lang/ka.js", //
			"lang/km.js", //
			"lang/ko.js", //
			"lang/ku.js", //
			"lang/lt.js", //
			"lang/lv.js", //
			"lang/mk.js", //
			"lang/mn.js", //
			"lang/ms.js", //
			"lang/nb.js", //
			"lang/nl.js", //
			"lang/no.js", //
			"lang/pl.js", //
			"lang/pt-br.js", //
			"lang/pt.js", //
			"lang/ro.js", //
			"lang/ru.js", //
			"lang/si.js", //
			"lang/sk.js", //
			"lang/sl.js", //
			"lang/sq.js", //
			"lang/sr-latn.js", //
			"lang/sr.js", //
			"lang/sv.js", //
			"lang/th.js", //
			"lang/tr.js", //
			"lang/ug.js", //
			"lang/uk.js", //
			"lang/vi.js", //
			"lang/zh-cn.js", //
			"lang/zh.js", //
			"plugins/icons.png", //
			"plugins/icons_hidpi.png", //
			"plugins/a11yhelp/dialogs/a11yhelp.js", //
			"plugins/a11yhelp/dialogs/lang/ar.js", //
			"plugins/a11yhelp/dialogs/lang/bg.js", //
			"plugins/a11yhelp/dialogs/lang/ca.js", //
			"plugins/a11yhelp/dialogs/lang/cs.js", //
			"plugins/a11yhelp/dialogs/lang/cy.js", //
			"plugins/a11yhelp/dialogs/lang/da.js", //
			"plugins/a11yhelp/dialogs/lang/de.js", //
			"plugins/a11yhelp/dialogs/lang/el.js", //
			"plugins/a11yhelp/dialogs/lang/en.js", //
			"plugins/a11yhelp/dialogs/lang/eo.js", //
			"plugins/a11yhelp/dialogs/lang/es.js", //
			"plugins/a11yhelp/dialogs/lang/et.js", //
			"plugins/a11yhelp/dialogs/lang/fa.js", //
			"plugins/a11yhelp/dialogs/lang/fi.js", //
			"plugins/a11yhelp/dialogs/lang/fr-ca.js", //
			"plugins/a11yhelp/dialogs/lang/fr.js", //
			"plugins/a11yhelp/dialogs/lang/gl.js", //
			"plugins/a11yhelp/dialogs/lang/gu.js", //
			"plugins/a11yhelp/dialogs/lang/he.js", //
			"plugins/a11yhelp/dialogs/lang/hi.js", //
			"plugins/a11yhelp/dialogs/lang/hr.js", //
			"plugins/a11yhelp/dialogs/lang/hu.js", //
			"plugins/a11yhelp/dialogs/lang/id.js", //
			"plugins/a11yhelp/dialogs/lang/it.js", //
			"plugins/a11yhelp/dialogs/lang/ja.js", //
			"plugins/a11yhelp/dialogs/lang/km.js", //
			"plugins/a11yhelp/dialogs/lang/ko.js", //
			"plugins/a11yhelp/dialogs/lang/ku.js", //
			"plugins/a11yhelp/dialogs/lang/lt.js", //
			"plugins/a11yhelp/dialogs/lang/lv.js", //
			"plugins/a11yhelp/dialogs/lang/mk.js", //
			"plugins/a11yhelp/dialogs/lang/mn.js", //
			"plugins/a11yhelp/dialogs/lang/nb.js", //
			"plugins/a11yhelp/dialogs/lang/nl.js", //
			"plugins/a11yhelp/dialogs/lang/no.js", //
			"plugins/a11yhelp/dialogs/lang/pl.js", //
			"plugins/a11yhelp/dialogs/lang/pt-br.js", //
			"plugins/a11yhelp/dialogs/lang/pt.js", //
			"plugins/a11yhelp/dialogs/lang/ro.js", //
			"plugins/a11yhelp/dialogs/lang/ru.js", //
			"plugins/a11yhelp/dialogs/lang/si.js", //
			"plugins/a11yhelp/dialogs/lang/sk.js", //
			"plugins/a11yhelp/dialogs/lang/sl.js", //
			"plugins/a11yhelp/dialogs/lang/sq.js", //
			"plugins/a11yhelp/dialogs/lang/sr-latn.js", //
			"plugins/a11yhelp/dialogs/lang/sr.js", //
			"plugins/a11yhelp/dialogs/lang/sv.js", //
			"plugins/a11yhelp/dialogs/lang/th.js", //
			"plugins/a11yhelp/dialogs/lang/tr.js", //
			"plugins/a11yhelp/dialogs/lang/ug.js", //
			"plugins/a11yhelp/dialogs/lang/uk.js", //
			"plugins/a11yhelp/dialogs/lang/vi.js", //
			"plugins/a11yhelp/dialogs/lang/zh-cn.js", //
			"plugins/a11yhelp/dialogs/lang/_translationstatus.txt", //
			"plugins/about/dialogs/about.js", //
			"plugins/about/dialogs/logo_ckeditor.png", //
			"plugins/about/dialogs/hidpi/logo_ckeditor.png", //
			"plugins/clipboard/plugin.js", //
			"plugins/clipboard/dev/clipboard.html", //
			"plugins/clipboard/dialogs/paste.js", //
			"plugins/clipboard/icons/copy-rtl.png", //
			"plugins/clipboard/icons/copy.png", //
			"plugins/clipboard/icons/cut-rtl.png", //
			"plugins/clipboard/icons/cut.png", //
			"plugins/clipboard/icons/paste-rtl.png", //
			"plugins/clipboard/icons/paste.png", //
			"plugins/clipboard/icons/hidpi/copy-rtl.png", //
			"plugins/clipboard/icons/hidpi/copy.png", //
			"plugins/clipboard/icons/hidpi/cut-rtl.png", //
			"plugins/clipboard/icons/hidpi/cut.png", //
			"plugins/clipboard/icons/hidpi/paste-rtl.png", //
			"plugins/clipboard/icons/hidpi/paste.png", //
			"plugins/clipboard/lang/af.js", //
			"plugins/clipboard/lang/ar.js", //
			"plugins/clipboard/lang/bg.js", //
			"plugins/clipboard/lang/bn.js", //
			"plugins/clipboard/lang/bs.js", //
			"plugins/clipboard/lang/ca.js", //
			"plugins/clipboard/lang/cs.js", //
			"plugins/clipboard/lang/cy.js", //
			"plugins/clipboard/lang/da.js", //
			"plugins/clipboard/lang/de.js", //
			"plugins/clipboard/lang/el.js", //
			"plugins/clipboard/lang/en-au.js", //
			"plugins/clipboard/lang/en-ca.js", //
			"plugins/clipboard/lang/en-gb.js", //
			"plugins/clipboard/lang/en.js", //
			"plugins/clipboard/lang/eo.js", //
			"plugins/clipboard/lang/es.js", //
			"plugins/clipboard/lang/et.js", //
			"plugins/clipboard/lang/eu.js", //
			"plugins/clipboard/lang/fa.js", //
			"plugins/clipboard/lang/fi.js", //
			"plugins/clipboard/lang/fo.js", //
			"plugins/clipboard/lang/fr-ca.js", //
			"plugins/clipboard/lang/fr.js", //
			"plugins/clipboard/lang/gl.js", //
			"plugins/clipboard/lang/gu.js", //
			"plugins/clipboard/lang/he.js", //
			"plugins/clipboard/lang/hi.js", //
			"plugins/clipboard/lang/hr.js", //
			"plugins/clipboard/lang/hu.js", //
			"plugins/clipboard/lang/id.js", //
			"plugins/clipboard/lang/is.js", //
			"plugins/clipboard/lang/it.js", //
			"plugins/clipboard/lang/ja.js", //
			"plugins/clipboard/lang/ka.js", //
			"plugins/clipboard/lang/km.js", //
			"plugins/clipboard/lang/ko.js", //
			"plugins/clipboard/lang/ku.js", //
			"plugins/clipboard/lang/lt.js", //
			"plugins/clipboard/lang/lv.js", //
			"plugins/clipboard/lang/mk.js", //
			"plugins/clipboard/lang/mn.js", //
			"plugins/clipboard/lang/ms.js", //
			"plugins/clipboard/lang/nb.js", //
			"plugins/clipboard/lang/nl.js", //
			"plugins/clipboard/lang/no.js", //
			"plugins/clipboard/lang/pl.js", //
			"plugins/clipboard/lang/pt-br.js", //
			"plugins/clipboard/lang/pt.js", //
			"plugins/clipboard/lang/ro.js", //
			"plugins/clipboard/lang/ru.js", //
			"plugins/clipboard/lang/si.js", //
			"plugins/clipboard/lang/sk.js", //
			"plugins/clipboard/lang/sl.js", //
			"plugins/clipboard/lang/sq.js", //
			"plugins/clipboard/lang/sr-latn.js", //
			"plugins/clipboard/lang/sr.js", //
			"plugins/clipboard/lang/sv.js", //
			"plugins/clipboard/lang/th.js", //
			"plugins/clipboard/lang/tr.js", //
			"plugins/clipboard/lang/tt.js", //
			"plugins/clipboard/lang/ug.js", //
			"plugins/clipboard/lang/uk.js", //
			"plugins/clipboard/lang/vi.js", //
			"plugins/clipboard/lang/zh-cn.js", //
			"plugins/clipboard/lang/zh.js", //
			"plugins/colorbutton/plugin.js", //
			"plugins/colorbutton/icons/bgcolor.png", //
			"plugins/colorbutton/icons/textcolor.png", //
			"plugins/colorbutton/icons/hidpi/bgcolor.png", //
			"plugins/colorbutton/icons/hidpi/textcolor.png", //
			"plugins/colorbutton/lang/af.js", //
			"plugins/colorbutton/lang/ar.js", //
			"plugins/colorbutton/lang/bg.js", //
			"plugins/colorbutton/lang/bn.js", //
			"plugins/colorbutton/lang/bs.js", //
			"plugins/colorbutton/lang/ca.js", //
			"plugins/colorbutton/lang/cs.js", //
			"plugins/colorbutton/lang/cy.js", //
			"plugins/colorbutton/lang/da.js", //
			"plugins/colorbutton/lang/de.js", //
			"plugins/colorbutton/lang/el.js", //
			"plugins/colorbutton/lang/en-au.js", //
			"plugins/colorbutton/lang/en-ca.js", //
			"plugins/colorbutton/lang/en-gb.js", //
			"plugins/colorbutton/lang/en.js", //
			"plugins/colorbutton/lang/eo.js", //
			"plugins/colorbutton/lang/es.js", //
			"plugins/colorbutton/lang/et.js", //
			"plugins/colorbutton/lang/eu.js", //
			"plugins/colorbutton/lang/fa.js", //
			"plugins/colorbutton/lang/fi.js", //
			"plugins/colorbutton/lang/fo.js", //
			"plugins/colorbutton/lang/fr-ca.js", //
			"plugins/colorbutton/lang/fr.js", //
			"plugins/colorbutton/lang/gl.js", //
			"plugins/colorbutton/lang/gu.js", //
			"plugins/colorbutton/lang/he.js", //
			"plugins/colorbutton/lang/hi.js", //
			"plugins/colorbutton/lang/hr.js", //
			"plugins/colorbutton/lang/hu.js", //
			"plugins/colorbutton/lang/id.js", //
			"plugins/colorbutton/lang/is.js", //
			"plugins/colorbutton/lang/it.js", //
			"plugins/colorbutton/lang/ja.js", //
			"plugins/colorbutton/lang/ka.js", //
			"plugins/colorbutton/lang/km.js", //
			"plugins/colorbutton/lang/ko.js", //
			"plugins/colorbutton/lang/ku.js", //
			"plugins/colorbutton/lang/lt.js", //
			"plugins/colorbutton/lang/lv.js", //
			"plugins/colorbutton/lang/mk.js", //
			"plugins/colorbutton/lang/mn.js", //
			"plugins/colorbutton/lang/ms.js", //
			"plugins/colorbutton/lang/nb.js", //
			"plugins/colorbutton/lang/nl.js", //
			"plugins/colorbutton/lang/no.js", //
			"plugins/colorbutton/lang/pl.js", //
			"plugins/colorbutton/lang/pt-br.js", //
			"plugins/colorbutton/lang/pt.js", //
			"plugins/colorbutton/lang/ro.js", //
			"plugins/colorbutton/lang/ru.js", //
			"plugins/colorbutton/lang/si.js", //
			"plugins/colorbutton/lang/sk.js", //
			"plugins/colorbutton/lang/sl.js", //
			"plugins/colorbutton/lang/sq.js", //
			"plugins/colorbutton/lang/sr-latn.js", //
			"plugins/colorbutton/lang/sr.js", //
			"plugins/colorbutton/lang/sv.js", //
			"plugins/colorbutton/lang/th.js", //
			"plugins/colorbutton/lang/tr.js", //
			"plugins/colorbutton/lang/tt.js", //
			"plugins/colorbutton/lang/ug.js", //
			"plugins/colorbutton/lang/uk.js", //
			"plugins/colorbutton/lang/vi.js", //
			"plugins/colorbutton/lang/zh-cn.js", //
			"plugins/colorbutton/lang/zh.js", //
			"plugins/dialog/dialogDefinition.js", //
			"plugins/dialog/plugin.js", //
			"plugins/dialog/samples/dialog.html", //
			"plugins/dialog/samples/assets/my_dialog.js", //
			"plugins/dialogui/plugin.js", //
			"plugins/dinavier/icons/dinavier.png",
			"plugins/dinavier/plugin.js",
			"plugins/fakeobjects/images/spacer.gif", //
			"plugins/font/plugin.js", //
			"plugins/font/lang/af.js", //
			"plugins/font/lang/ar.js", //
			"plugins/font/lang/bg.js", //
			"plugins/font/lang/bn.js", //
			"plugins/font/lang/bs.js", //
			"plugins/font/lang/ca.js", //
			"plugins/font/lang/cs.js", //
			"plugins/font/lang/cy.js", //
			"plugins/font/lang/da.js", //
			"plugins/font/lang/de.js", //
			"plugins/font/lang/el.js", //
			"plugins/font/lang/en-au.js", //
			"plugins/font/lang/en-ca.js", //
			"plugins/font/lang/en-gb.js", //
			"plugins/font/lang/en.js", //
			"plugins/font/lang/eo.js", //
			"plugins/font/lang/es.js", //
			"plugins/font/lang/et.js", //
			"plugins/font/lang/eu.js", //
			"plugins/font/lang/fa.js", //
			"plugins/font/lang/fi.js", //
			"plugins/font/lang/fo.js", //
			"plugins/font/lang/fr-ca.js", //
			"plugins/font/lang/fr.js", //
			"plugins/font/lang/gl.js", //
			"plugins/font/lang/gu.js", //
			"plugins/font/lang/he.js", //
			"plugins/font/lang/hi.js", //
			"plugins/font/lang/hr.js", //
			"plugins/font/lang/hu.js", //
			"plugins/font/lang/id.js", //
			"plugins/font/lang/is.js", //
			"plugins/font/lang/it.js", //
			"plugins/font/lang/ja.js", //
			"plugins/font/lang/ka.js", //
			"plugins/font/lang/km.js", //
			"plugins/font/lang/ko.js", //
			"plugins/font/lang/ku.js", //
			"plugins/font/lang/lt.js", //
			"plugins/font/lang/lv.js", //
			"plugins/font/lang/mk.js", //
			"plugins/font/lang/mn.js", //
			"plugins/font/lang/ms.js", //
			"plugins/font/lang/nb.js", //
			"plugins/font/lang/nl.js", //
			"plugins/font/lang/no.js", //
			"plugins/font/lang/pl.js", //
			"plugins/font/lang/pt-br.js", //
			"plugins/font/lang/pt.js", //
			"plugins/font/lang/ro.js", //
			"plugins/font/lang/ru.js", //
			"plugins/font/lang/si.js", //
			"plugins/font/lang/sk.js", //
			"plugins/font/lang/sl.js", //
			"plugins/font/lang/sq.js", //
			"plugins/font/lang/sr-latn.js", //
			"plugins/font/lang/sr.js", //
			"plugins/font/lang/sv.js", //
			"plugins/font/lang/th.js", //
			"plugins/font/lang/tr.js", //
			"plugins/font/lang/ug.js", //
			"plugins/font/lang/uk.js", //
			"plugins/font/lang/vi.js", //
			"plugins/font/lang/zh-cn.js", //
			"plugins/font/lang/zh.js", //
			"plugins/image/dialogs/image.js", //
			"plugins/image/images/noimage.png", //
			"plugins/link/dialogs/anchor.js", //
			"plugins/link/dialogs/link.js", //
			"plugins/link/images/anchor.png", //
			"plugins/link/images/hidpi/anchor.png", //
			"plugins/liststyle/plugin.js", //
			"plugins/liststyle/dialogs/liststyle.js", //
			"plugins/liststyle/lang/af.js", //
			"plugins/liststyle/lang/ar.js", //
			"plugins/liststyle/lang/bg.js", //
			"plugins/liststyle/lang/bn.js", //
			"plugins/liststyle/lang/bs.js", //
			"plugins/liststyle/lang/ca.js", //
			"plugins/liststyle/lang/cs.js", //
			"plugins/liststyle/lang/cy.js", //
			"plugins/liststyle/lang/da.js", //
			"plugins/liststyle/lang/de.js", //
			"plugins/liststyle/lang/el.js", //
			"plugins/liststyle/lang/en-au.js", //
			"plugins/liststyle/lang/en-ca.js", //
			"plugins/liststyle/lang/en-gb.js", //
			"plugins/liststyle/lang/en.js", //
			"plugins/liststyle/lang/eo.js", //
			"plugins/liststyle/lang/es.js", //
			"plugins/liststyle/lang/et.js", //
			"plugins/liststyle/lang/eu.js", //
			"plugins/liststyle/lang/fa.js", //
			"plugins/liststyle/lang/fi.js", //
			"plugins/liststyle/lang/fo.js", //
			"plugins/liststyle/lang/fr-ca.js", //
			"plugins/liststyle/lang/fr.js", //
			"plugins/liststyle/lang/gl.js", //
			"plugins/liststyle/lang/gu.js", //
			"plugins/liststyle/lang/he.js", //
			"plugins/liststyle/lang/hi.js", //
			"plugins/liststyle/lang/hr.js", //
			"plugins/liststyle/lang/hu.js", //
			"plugins/liststyle/lang/id.js", //
			"plugins/liststyle/lang/is.js", //
			"plugins/liststyle/lang/it.js", //
			"plugins/liststyle/lang/ja.js", //
			"plugins/liststyle/lang/ka.js", //
			"plugins/liststyle/lang/km.js", //
			"plugins/liststyle/lang/ko.js", //
			"plugins/liststyle/lang/ku.js", //
			"plugins/liststyle/lang/lt.js", //
			"plugins/liststyle/lang/lv.js", //
			"plugins/liststyle/lang/mk.js", //
			"plugins/liststyle/lang/mn.js", //
			"plugins/liststyle/lang/ms.js", //
			"plugins/liststyle/lang/nb.js", //
			"plugins/liststyle/lang/nl.js", //
			"plugins/liststyle/lang/no.js", //
			"plugins/liststyle/lang/pl.js", //
			"plugins/liststyle/lang/pt-br.js", //
			"plugins/liststyle/lang/pt.js", //
			"plugins/liststyle/lang/ro.js", //
			"plugins/liststyle/lang/ru.js", //
			"plugins/liststyle/lang/si.js", //
			"plugins/liststyle/lang/sk.js", //
			"plugins/liststyle/lang/sl.js", //
			"plugins/liststyle/lang/sq.js", //
			"plugins/liststyle/lang/sr-latn.js", //
			"plugins/liststyle/lang/sr.js", //
			"plugins/liststyle/lang/sv.js", //
			"plugins/liststyle/lang/th.js", //
			"plugins/liststyle/lang/tr.js", //
			"plugins/liststyle/lang/ug.js", //
			"plugins/liststyle/lang/uk.js", //
			"plugins/liststyle/lang/vi.js", //
			"plugins/liststyle/lang/zh-cn.js", //
			"plugins/liststyle/lang/zh.js", //
			"plugins/magicline/images/icon.png", //
			"plugins/magicline/images/hidpi/icon.png", //
			"plugins/onchange/plugin.js", //
			"plugins/onchange/docs/install.html", //
			"plugins/onchange/docs/styles.css", //
			"plugins/panelbutton/plugin.js", //
			"plugins/pastefromword/plugin.js", //
			"plugins/pastefromword/filter/default.js", //
			"plugins/pastefromword/icons/pastefromword-rtl.png", //
			"plugins/pastefromword/icons/pastefromword.png", //
			"plugins/pastefromword/icons/hidpi/pastefromword-rtl.png", //
			"plugins/pastefromword/icons/hidpi/pastefromword.png", //
			"plugins/pastefromword/lang/af.js", //
			"plugins/pastefromword/lang/ar.js", //
			"plugins/pastefromword/lang/bg.js", //
			"plugins/pastefromword/lang/bn.js", //
			"plugins/pastefromword/lang/bs.js", //
			"plugins/pastefromword/lang/ca.js", //
			"plugins/pastefromword/lang/cs.js", //
			"plugins/pastefromword/lang/cy.js", //
			"plugins/pastefromword/lang/da.js", //
			"plugins/pastefromword/lang/de.js", //
			"plugins/pastefromword/lang/el.js", //
			"plugins/pastefromword/lang/en-au.js", //
			"plugins/pastefromword/lang/en-ca.js", //
			"plugins/pastefromword/lang/en-gb.js", //
			"plugins/pastefromword/lang/en.js", //
			"plugins/pastefromword/lang/eo.js", //
			"plugins/pastefromword/lang/es.js", //
			"plugins/pastefromword/lang/et.js", //
			"plugins/pastefromword/lang/eu.js", //
			"plugins/pastefromword/lang/fa.js", //
			"plugins/pastefromword/lang/fi.js", //
			"plugins/pastefromword/lang/fo.js", //
			"plugins/pastefromword/lang/fr-ca.js", //
			"plugins/pastefromword/lang/fr.js", //
			"plugins/pastefromword/lang/gl.js", //
			"plugins/pastefromword/lang/gu.js", //
			"plugins/pastefromword/lang/he.js", //
			"plugins/pastefromword/lang/hi.js", //
			"plugins/pastefromword/lang/hr.js", //
			"plugins/pastefromword/lang/hu.js", //
			"plugins/pastefromword/lang/id.js", //
			"plugins/pastefromword/lang/is.js", //
			"plugins/pastefromword/lang/it.js", //
			"plugins/pastefromword/lang/ja.js", //
			"plugins/pastefromword/lang/ka.js", //
			"plugins/pastefromword/lang/km.js", //
			"plugins/pastefromword/lang/ko.js", //
			"plugins/pastefromword/lang/ku.js", //
			"plugins/pastefromword/lang/lt.js", //
			"plugins/pastefromword/lang/lv.js", //
			"plugins/pastefromword/lang/mk.js", //
			"plugins/pastefromword/lang/mn.js", //
			"plugins/pastefromword/lang/ms.js", //
			"plugins/pastefromword/lang/nb.js", //
			"plugins/pastefromword/lang/nl.js", //
			"plugins/pastefromword/lang/no.js", //
			"plugins/pastefromword/lang/pl.js", //
			"plugins/pastefromword/lang/pt-br.js", //
			"plugins/pastefromword/lang/pt.js", //
			"plugins/pastefromword/lang/ro.js", //
			"plugins/pastefromword/lang/ru.js", //
			"plugins/pastefromword/lang/si.js", //
			"plugins/pastefromword/lang/sk.js", //
			"plugins/pastefromword/lang/sl.js", //
			"plugins/pastefromword/lang/sq.js", //
			"plugins/pastefromword/lang/sr-latn.js", //
			"plugins/pastefromword/lang/sr.js", //
			"plugins/pastefromword/lang/sv.js", //
			"plugins/pastefromword/lang/th.js", //
			"plugins/pastefromword/lang/tr.js", //
			"plugins/pastefromword/lang/tt.js", //
			"plugins/pastefromword/lang/ug.js", //
			"plugins/pastefromword/lang/uk.js", //
			"plugins/pastefromword/lang/vi.js", //
			"plugins/pastefromword/lang/zh-cn.js", //
			"plugins/pastefromword/lang/zh.js", //
			"plugins/scayt/LICENSE.md", //
			"plugins/scayt/README.md", //
			"plugins/scayt/dialogs/options.js", //
			"plugins/scayt/dialogs/toolbar.css", //
			"plugins/showborders/plugin.js", //
			"plugins/specialchar/dialogs/specialchar.js", //
			"plugins/specialchar/dialogs/lang/ar.js", //
			"plugins/specialchar/dialogs/lang/bg.js", //
			"plugins/specialchar/dialogs/lang/ca.js", //
			"plugins/specialchar/dialogs/lang/cs.js", //
			"plugins/specialchar/dialogs/lang/cy.js", //
			"plugins/specialchar/dialogs/lang/de.js", //
			"plugins/specialchar/dialogs/lang/el.js", //
			"plugins/specialchar/dialogs/lang/en.js", //
			"plugins/specialchar/dialogs/lang/eo.js", //
			"plugins/specialchar/dialogs/lang/es.js", //
			"plugins/specialchar/dialogs/lang/et.js", //
			"plugins/specialchar/dialogs/lang/fa.js", //
			"plugins/specialchar/dialogs/lang/fi.js", //
			"plugins/specialchar/dialogs/lang/fr-ca.js", //
			"plugins/specialchar/dialogs/lang/fr.js", //
			"plugins/specialchar/dialogs/lang/gl.js", //
			"plugins/specialchar/dialogs/lang/he.js", //
			"plugins/specialchar/dialogs/lang/hr.js", //
			"plugins/specialchar/dialogs/lang/hu.js", //
			"plugins/specialchar/dialogs/lang/id.js", //
			"plugins/specialchar/dialogs/lang/it.js", //
			"plugins/specialchar/dialogs/lang/ja.js", //
			"plugins/specialchar/dialogs/lang/km.js", //
			"plugins/specialchar/dialogs/lang/ku.js", //
			"plugins/specialchar/dialogs/lang/lv.js", //
			"plugins/specialchar/dialogs/lang/nb.js", //
			"plugins/specialchar/dialogs/lang/nl.js", //
			"plugins/specialchar/dialogs/lang/no.js", //
			"plugins/specialchar/dialogs/lang/pl.js", //
			"plugins/specialchar/dialogs/lang/pt-br.js", //
			"plugins/specialchar/dialogs/lang/pt.js", //
			"plugins/specialchar/dialogs/lang/ru.js", //
			"plugins/specialchar/dialogs/lang/si.js", //
			"plugins/specialchar/dialogs/lang/sk.js", //
			"plugins/specialchar/dialogs/lang/sl.js", //
			"plugins/specialchar/dialogs/lang/sq.js", //
			"plugins/specialchar/dialogs/lang/sv.js", //
			"plugins/specialchar/dialogs/lang/th.js", //
			"plugins/specialchar/dialogs/lang/tr.js", //
			"plugins/specialchar/dialogs/lang/ug.js", //
			"plugins/specialchar/dialogs/lang/uk.js", //
			"plugins/specialchar/dialogs/lang/vi.js", //
			"plugins/specialchar/dialogs/lang/zh-cn.js", //
			"plugins/specialchar/dialogs/lang/_translationstatus.txt", //
			"plugins/table/dialogs/table.js", //
			"plugins/tabletools/dialogs/tableCell.js", //
			"plugins/wsc/LICENSE.md", //
			"plugins/wsc/README.md", //
			"plugins/wsc/dialogs/ciframe.html", //
			"plugins/wsc/dialogs/tmp.html", //
			"plugins/wsc/dialogs/tmpFrameset.html", //
			"plugins/wsc/dialogs/wsc.css", //
			"plugins/wsc/dialogs/wsc.js", //
			"plugins/wsc/dialogs/wsc_ie.js", //
			"skins/kama/editor.css", //
			"skins/kama/icons.png", //
			"skins/kama/images/sprites.png", //
			"skins/kama/images/sprites_ie6.png", //
			"skins/moono/dialog.css", //
			"skins/moono/dialog_ie.css", //
			"skins/moono/dialog_ie7.css", //
			"skins/moono/dialog_ie8.css", //
			"skins/moono/dialog_iequirks.css", //
			"skins/moono/dialog_opera.css", //
			"skins/moono/editor.css", //
			"skins/moono/editor_gecko.css", //
			"skins/moono/editor_ie.css", //
			"skins/moono/editor_ie7.css", //
			"skins/moono/editor_ie8.css", //
			"skins/moono/editor_iequirks.css", //
			"skins/moono/icons.png", //
			"skins/moono/icons_hidpi.png", //
			"skins/moono/readme.md", //
			"skins/moono/images/arrow.png", //
			"skins/moono/images/close.png", //
			"skins/moono/images/lock-open.png", //
			"skins/moono/images/lock.png", //
			"skins/moono/images/refresh.png", //
			"skins/moono/images/hidpi/close.png", //
			"skins/moono/images/hidpi/lock-open.png", //
			"skins/moono/images/hidpi/lock.png", //
			"skins/moono/images/hidpi/refresh.png" //
	};

	private String text = "";
	Browser browser;
	boolean clientReady = false;
	private StringBuilder scriptBuffer = null;

	public CKEditor(final Composite parent, final int style) {
		super(parent, style);
		super.setLayout(new FillLayout());
		setBackgroundMode(SWT.INHERIT_FORCE);
		registerResources();
		browser = new Browser(this, SWT.BORDER);
		browser.setUrl(getEditorHtmlLocation());
		addBrowserHandler();
	}

	private void registerResources() {
		final IResourceManager resourceManager = RWT.getResourceManager();
		final boolean isRegistered = resourceManager.isRegistered(REGISTER_PATH + RESOURCE_FILES[0]);
		if (!isRegistered) {
			try {
				for (final String fileName : RESOURCE_FILES) {
					register(resourceManager, fileName);
				}
			} catch (final IOException ioe) {
				throw new IllegalArgumentException("Failed to load resources", ioe);
			}
		}
	}

	private String getEditorHtmlLocation() {
		final IResourceManager resourceManager = RWT.getResourceManager();
		return resourceManager.getLocation(REGISTER_PATH + RESOURCE_FILES[0]);
	}

	private void register(final IResourceManager resourceManager, final String fileName) throws IOException {
		final ClassLoader classLoader = CKEditor.class.getClassLoader();
		final InputStream inputStream = classLoader.getResourceAsStream(RESOURCES_PATH + fileName);
		try {
			resourceManager.register(REGISTER_PATH + fileName, inputStream);
		} finally {
			inputStream.close();
		}
	}

	////////////////////
	// overwrite methods

	@Override
	public void setLayout(final Layout layout) {
		throw new UnsupportedOperationException("Cannot change internal layout of CkEditor");
	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		writeFont();
	}

	//////
	// API

	public void setText(final String text) {
		checkWidget();
		if (text == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		this.text = text;
		writeText();
		clientReady = false; // order is important
	}

	public String getText() {
		checkWidget();
		readText();
		return text;
	}

	//////////////
	// browser I/O

	void onReady() {
		writeFont(); // CKEditor re-creates the document with every setData, losing inline styles
		evalScriptBuffer();
		clientReady = true;
	}

	private void writeText() {
		evalOnReady("rap.editor.setData( \"" + escapeText(text) + "\" );");
	}

	private void writeFont() {
		evalOnReady("rap.editor.document.getBody().setStyle( \"font\", \"" + getCssFont() + "\" );");
	}

	private void readText() {
		if (clientReady) {
			text = (String) browser.evaluate("return rap.editor.getData();");
		}
	}

	/////////
	// helper

	private void addBrowserHandler() {
		new BrowserFunction(browser, READY_FUNCTION) {
			@Override
			public Object function(final Object[] arguments) {
				onReady();
				return null;
			}
		};
	}

	private void evalOnReady(final String script) {
		if (clientReady) {
			browser.evaluate(script);
		} else {
			if (scriptBuffer == null) {
				scriptBuffer = new StringBuilder();
			}
			scriptBuffer.append(script);
		}
	}

	private void evalScriptBuffer() {
		if (scriptBuffer != null) {
			browser.evaluate(scriptBuffer.toString());
			scriptBuffer = null;
		}
	}

	private String getCssFont() {
		final StringBuilder result = new StringBuilder();
		if (getFont() != null) {
			final FontData data = getFont().getFontData()[0];
			result.append(data.getHeight());
			result.append("px ");
			result.append(escapeText(data.getName()));
		}
		return result.toString();
	}

	private static String escapeText(final String text) {
		// escaping backslashes, double-quotes, newlines, and carriage-return
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			final char ch = text.charAt(i);
			if (ch == '\n') {
				result.append("\\n");
			} else if (ch == '\r') {
				result.append("\\r");
			} else if (ch == '\\') {
				result.append("\\\\");
			} else if (ch == '"') {
				result.append("\\\"");
			} else {
				result.append(ch);
			}
		}
		return result.toString();
	}

}
