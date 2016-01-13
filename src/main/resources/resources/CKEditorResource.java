/*
 *  (c) tolina GmbH, 2014
 */
package de.tolina.common.gui.rwt.ckeditor;

import org.apache.commons.lang.CharEncoding;
import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;


/**
 * Die Klasse definiert Ressourcen für den CKEditor
 * @author TOLINA GmbH
 */
public class CKEditorResource implements IResource {

	@Override
	public ClassLoader getLoader() {
		return this.getClass().getClassLoader();
	}

	@Override
	public String getLocation() {
		return "de/tolina/common/gui/rwt/ckeditor/ckeditor.js";
	}

	@Override
	public String getCharset() {
		return CharEncoding.UTF_8;
	}

	@Override
	public RegisterOptions getOptions() {
		return RegisterOptions.NONE;
	}

	@Override
	public boolean isJSLibrary() {
		return true;
	}

	@Override
	public boolean isExternal() {
		return true;
	}

}
