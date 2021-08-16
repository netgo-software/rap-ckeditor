CKEDITOR.plugins.add('dinavier', {
    icons: 'dinavier',
    init: function (editor) {
        editor.addCommand('dinaVier', {
            exec: function (editor) {
                editor.getCommand('dinaVier').toggleState();
                editor.document.getBody().hasClass('din-a4') ? editor.document.getBody().removeClass('din-a4') : editor.document.getBody().addClass('din-a4');
                if (editor.getCommand('dinaVier').state === CKEDITOR.TRISTATE_OFF) {
                    editor.document.getBody().removeStyle('min-height');
                    editor.document.$.documentElement.style.backgroundColor = "#fff";
                } else {
                    var hoehe = editor.ui.space('contents').getStyle('height');
                    hoehe = parseInt(hoehe.substring(0, hoehe.length - 2)) - 28 + "px";
                    hoehe = hoehe + ' !important';
                    editor.document.getBody().$.style.cssText = 'min-height: '+ hoehe;
                    editor.document.$.documentElement.style.backgroundColor = "#dfdfdf";
                }
                
            }
        });
        editor.ui.addButton('dinavier', {
            label: 'Umstellen auf DIN A4',
            command: 'dinaVier',
            toolbar: 'dinavier'
        });
    }
});