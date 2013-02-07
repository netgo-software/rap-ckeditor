describe("eclipsesource.CKEditor", function() {

  var editor;

  var createEditor = function() {
    editor = new eclipsesource.CKEditor( { "parent" : "w2" } );
  };

  it( "should get a Composite with parent id", function() {
    spyOn( rap, "getObject" );

    createEditor();

    expect( rap.getObject ).toHaveBeenCalledWith( "w2" );
  } );

} );