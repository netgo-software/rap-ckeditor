var CKEDITOR_BASEPATH = "rwt-resources/ckeditor/";

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
    bindAll( this, [ "layout", "onReady" ] );
    this.parent = rap.getObject( properties.parent );
    this.element = document.createElement( "div" );
    this.parent.append( this.element );
    this.editor = CKEDITOR.appendTo( this.element );
    this.parent.addListener( "Resize", this.layout );
    this.editor.on( "instanceReady", this.onReady );
  };

  eclipsesource.CKEditor.prototype = {

    ready : false,

    onReady : function() {
      this.ready = true;
      this.layout();
    },

    setText : function( text ) {
      console.log( "setText", text );
    },

    setFont : function( font ) {
      console.log( "setFont", font );
    },

    destroy : function() {
      console.log( "destroy" );
    },

    layout : function() {
      if( this.ready ) {
        var area = this.parent.getClientArea();
        this.element.style.left = area[ 0 ] + "px";
        this.element.style.top = area[ 1 ] + "px";
        this.editor.resize( area[ 2 ], area[ 3 ] );
      }
    }

  };

  var bind = function( context, method ) {
    return function() {
      console.log( method, context, arguments );
      return method.apply( context, arguments );
    };
  };

  var bindAll = function( context, methodNames ) {
    for( var i = 0; i < methodNames.length; i++ ) {
      var method = context[ methodNames[ i ] ];
      context[ methodNames[ i ] ] = bind( context, method );
    }
  };

}());