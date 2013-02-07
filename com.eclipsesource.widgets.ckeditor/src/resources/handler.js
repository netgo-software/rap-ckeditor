(function(){
  'use strict';

  rap.registerTypeHandler( "eclipsesource.CKEditor", {

    factory : function( properties ) {
      return new eclipsesource.CKEditor( properties );
    },

    destructor : "destroy",

    properties : [ "text", "font" ]

  } );

  if( !window.eclipsesource ) {
    window.eclipsesource = {};
  }

  eclipsesource.CKEditor = function( properties ) {
    rap.getObject( properties.parent );
  };

  eclipsesource.CKEditor.prototype = {

    setText : function( text ) {
      console.log( "setText", text );
    },

    setFont : function( font ) {
      console.log( "setFont", font );
    },

    destroy : function() {
      console.log( "destroy" );
    }

  };

}());