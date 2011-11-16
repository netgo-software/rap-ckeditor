package com.eclipsesource.widgets.ckeditor;

import org.json.*;
import com.eclipsesource.widgets.ckeditor.Style;
import junit.framework.TestCase;

public class Style_Test extends TestCase {

  public void testToString() throws JSONException {
    Style style = new Style( "span" );
    
    JSONObject result = new JSONObject( style.toString() );
    
    assertEquals( 3, result.length() );
    assertEquals( "span", result.getString( "element" ) );
    assertEquals( 0, result.getJSONObject( "styles" ).length() );
    assertEquals( 0, result.getJSONObject( "attributes" ).length() );
  }
  
}
