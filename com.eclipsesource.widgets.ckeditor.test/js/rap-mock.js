rap = {

  /////////////////////////////////////////////////////
  // mock RemoteObject returned for rap.getRemoteObject

  fakeRemoteObject : {
    set : function( property, value ){},
    notify : function( event, properties ){},
    call : function( method, properties ){}
  },

  /////////////////////////////////////////////////////
  // mock Composite returned for rap.getObject

  fakeComposite : {
    append : function( node ){
      document.createElement( "div" ).appendChild( node );
    },
    addListener : function(){},
    removeListener : function(){},
    getClientArea : function(){ return [ 0, 0, 0, 0 ]; }
  },

  ///////////////////////////
  // stubs for public RAP API

  on: function( eventType, listener ) {},

  off: function( eventType, listener ) {},

  registerTypeHandler : function( typeHandler ) {},

  getObject : function( id ) {
    return this.fakeComposite;
  },

  getRemoteObject : function( clientObject ) {
    return this.fakeRemoteObject;
  }

};
