(function(){
  'use strict';

  var createStubs = function( object, names ) {
    var fun = function(){ return false; };
    for( var i = 0; i < names.length; i++ ) {
      object[ names[ i ] ] = fun;
    }
  };

  window.CKEditor = {
    editor : function(){},
    appendTo : function( element ) {
      return new CKEditor.editor( element );
    }
  };

  createStubs(
    CKEditor.editor.prototype,
    [ "on", "resize", "setData", "getData", "checkDirty", "resetDirty" ]
  );

  CKEditor.editor.prototype.document = {
    "getBody" : function() {
      return this.body;
    },
    "body" : {
      setStyle : function(){}
    }
  };

}());