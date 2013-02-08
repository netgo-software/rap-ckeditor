describe( "eclipsesource.CKEditor", function() {

  var editor;

  var createEditor = function() {
    editor = new eclipsesource.CKEditor( { "parent" : "w2" } );
  };

  afterEach( function() {
    editor = null;
  } );

  describe( "The Constructor", function() {

    it( "should get a Composite with the parent id", function() {
      spyOn( rap, "getObject" ).andCallThrough();
      createEditor();
      expect( rap.getObject ).toHaveBeenCalledWith( "w2" );
    } );

    it( "should add an element to the composite", function() {
      spyOn( rap.fakeComposite, "append" );
      createEditor();
      expect( rap.fakeComposite.append ).toHaveBeenCalledWith( editor.element );
    } );

    it( "should create an CKEditor instance with it's own element'", function() {
      spyOn( CKEDITOR, "appendTo" ).andCallThrough();
      createEditor();
      expect( CKEDITOR.appendTo ).toHaveBeenCalledWith( editor.element );
    } );

    it( "should add a resize listener", function() {
      spyOn( rap.fakeComposite, "addListener" );
      createEditor();
      expect( rap.fakeComposite.addListener ).toHaveBeenCalledWith( "Resize", editor.layout );
    } );

    it( "should add a ready listener", function() {
      spyOn( CKEDITOR.editor.prototype, "on" );
      createEditor();
      expect( editor.editor.on ).toHaveBeenCalledWith( "instanceReady", editor.onReady );
    } );

  } );

  describe( "The layout function", function() {

    beforeEach( function() {
      createEditor();
      spyOn( rap.fakeComposite, "getClientArea" ).andReturn( [ 1, 2, 100, 110 ] );
    } );

    var getPosition = function( element ) {
      return [
        parseInt( element.style.left, 10 ),
        parseInt( element.style.top, 10 )
      ];
    };

    describe( "of an editor that is not ready, ", function() {

      it( "does nothing on a Resize event", function() {
        spyOn( editor.editor, "resize" );
        editor.layout.call(); // call without context like the Resize event would
        expect( editor.editor.resize ).not.toHaveBeenCalled();
      } );

      it( "updates the outer element position on a ready event", function() {
        editor.onReady.call();
        expect( getPosition( editor.element ) ).toEqual( [ 1, 2 ] );
      } );

      it( "updates the editor element size on a ready event", function() {
        spyOn( editor.editor, "resize" );
        editor.onReady.call();
        expect( editor.editor.resize ).toHaveBeenCalledWith( 100, 110 );
      } );
    } );

    describe( "of an editor that is ready, ", function() {

      beforeEach( function() {
        editor.onReady.call();
      } );

      it( "updates the outer element position on a Resize event", function() {
        editor.layout.call();
        expect( getPosition( editor.element ) ).toEqual( [ 1, 2 ] );
      } );

      it( "updates the editor element size on a Resize event", function() {
        spyOn( editor.editor, "resize" );
        editor.layout.call();
        expect( editor.editor.resize ).toHaveBeenCalledWith( 100, 110 );
      } );

    } );

  } );

  describe( "The setText function", function() {

    beforeEach( function() {
      createEditor();
      spyOn( editor.editor, "setData" );
      editor.setText( "foo" );
    } );

    it( "does nothing when editor is not ready", function() {
      expect( editor.editor.setData ).not.toHaveBeenCalled();
    } );

    it( "calls setData on a ready event", function() {
      editor.onReady.call();
      expect( editor.editor.setData ).toHaveBeenCalledWith( "foo" );
    } );

    it( "calls setData after a ready event", function() {
      editor.onReady.call();
      editor.setText( "bar" );
      expect( editor.editor.setData ).toHaveBeenCalledWith( "bar" );
    } );

  } );

  describe( "The setFont function", function() {

    var body;

    beforeEach( function() {
      createEditor();
      body = editor.editor.document.getBody();
      spyOn( body, "setStyle" );
      editor.setFont( "13px Arial" );
    } );

    it( "does nothing when editor is not ready", function() {
      expect( body.setStyle ).not.toHaveBeenCalled();
    } );

    it( "calls body.setStyle on a ready event", function() {
      editor.onReady.call();
      expect( body.setStyle ).toHaveBeenCalledWith( "font", "13px Arial" );
    } );

    it( "calls body.setStyle after a ready event", function() {
      editor.onReady.call();
      editor.setFont( "15px Fantasy" );
      expect( body.setStyle ).toHaveBeenCalledWith( "font", "15px Fantasy" );
    } );

  } );


} );