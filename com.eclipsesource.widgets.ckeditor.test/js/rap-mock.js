rap = {

  _listeners: {},

  fakeRemoteObject : {
    set : function(){}
  },

  fakeComposite : {
    append : function(){},
    addListener : function(){},
    removeListener : function(){},
    getClientArea : function(){}
  },

  on: function( eventType, listener ) {
    if( !this._listeners[ eventType ] ) {
      this._listeners[ eventType ] = [];
    }
    this._listeners[ eventType ].push( listener );
  },

  off: function( eventType, listener ) {
    if( this._listeners[ eventType ] ) {
      var listeners = this._listeners[ eventType ];
      var index = -1;
      for( var i = 0; i < listeners.length; i++ ) {
        if( listeners[ i ] === listener ) {
          index = i;
        }
      }
      if( index !== -1 ) {
        listeners.splice( i, 1 );
      }
    }
  },

  registerTypeHandler: function( typeHandler ) {},

  getObject : function( id ) {
    return this.fakeComposite;
  },

  getRemoteObject : function( clientObject ) {
    return this.fakeRemoteObect;
  },

  setup: function() {
    this._listeners = {};
  },

  notify: function( eventType ) {
    if( this._listeners[ eventType ] ) {
      var listeners = this._listeners[ eventType ];
      for( var i = 0; i < listeners.length; i++ ) {
        listeners[ i ]();
      }
    }
  }

};
