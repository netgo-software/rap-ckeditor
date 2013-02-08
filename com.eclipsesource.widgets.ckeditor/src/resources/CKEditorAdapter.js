// TODO : solution without "guessed" path?
var CKEDITOR_BASEPATH = "rwt-resources/ckeditor/";

(function(){

var bind = function( context, method ) {
  return function() {
    return method.apply( context, arguments );
  };
};

var Wrapper = function( properties ) {
  this.onReady = bind( this, this.onReady );
  this.onSend = bind( this, this.onSend );
  this.onLayout = bind( this, this.onLayout );
  this.element = document.createElement( "div" ); // "glue" between editor and parent
  this.editor = CKEDITOR.appendTo( this.element );
  this.parent = rap.getObject( properties.parent );
  this.parent.append( this.element );
  this.editor.on( "instanceReady", this.onReady );
  this.parent.addListener( "Resize", this.onLayout );
  rap.on( "send", this.onSend );
};

Wrapper.prototype = {
  "ready" : false,
  "font" : "",
  "onLayout" : function() {
    if( this.ready ) {
      var area = this.parent.getClientArea();
      this.element.style.left = area[ 0 ] + "px";
      this.element.style.top = area[ 1 ] + "px";
      this.editor.resize( area[ 2 ], area[ 3 ] );
    }
  },
  "setFont" : function( font ) {
    this.font = font;
    this.applyFont();
  },
  "setText" : function( text ) {
    this.editor.setData( text );
  },
  "applyFont" : function() {
    if( this.ready ) {
      this.editor.document.getBody().setStyle( "font", this.font );
    }
  },
  "onSend" : function() {
    if( this.editor.checkDirty() ) {
      rap.getRemoteObject( this ).set( "text", this.editor.getData() );
      this.editor.resetDirty();
    }
  },
  "onReady" : function() {
    this.ready = true;
    this.onLayout();
    this.applyFont();
  },
  "destroy" : function() {
    rap.off( "send", this.onSend );
  }
};

rap.registerTypeHandler( "eclipsesource.CKEditor", {

  factory : function( properties ) {
    return new Wrapper( properties );
  },

  destructor : function( editor ) {
    editor.destroy();
  },

  properties : [ "text", "font" ]

} );

}());
