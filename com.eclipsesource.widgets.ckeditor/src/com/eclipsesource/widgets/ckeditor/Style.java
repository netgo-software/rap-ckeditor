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
package com.eclipsesource.widgets.ckeditor;

import org.json.JSONException;
import org.json.JSONObject;



public class Style {
  
  JSONObject json = new JSONObject();
 
  Style( String element ) {
    try {
      json.put( "element", element );
      json.put( "styles", new JSONObject() );
      json.put( "attributes", new JSONObject() );
    } catch( JSONException e ) {
      e.printStackTrace();
    }
  }
  
  @Override
  public String toString() {
    return json.toString();
  }

}
