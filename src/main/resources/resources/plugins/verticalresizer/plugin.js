//von ckeditor.js extrahiert
CKEDITOR.plugins.add('verticalresizer', {
    init: function (editor) {
        var config = editor.config;
        if (!config.verticalresizer_enabled) return;

        function f(d) {
            var e = c.width,
                m = c.height,
                f = e + (d.data.$.screenX - n.x) * ('rtl' == g ? -1 : 1);
            d = m + (d.data.$.screenY - n.y);
            h && (e = Math.max(config.resize_minWidth, Math.min(f, config.resize_maxWidth)));
            p && (m = Math.max(config.resize_minHeight, Math.min(d, config.resize_maxHeight)));
            editor.resize(h ? e : null, m);
        }

        function k() {
            CKEDITOR.document.removeListener('mousemove', f);
            CKEDITOR.document.removeListener('mouseup', k);
            editor.document &&
            (editor.document.removeListener('mousemove', f), editor.document.removeListener('mouseup', k));
        }

        var g = editor.element ? editor.element.getDirection(1) : 'ltr';
        !config.resize_dir && (config.resize_dir = "vertical");
        void 0 === config.resize_maxWidth && (config.resize_maxWidth = 3E3);
        void 0 === config.resize_maxHeight && (config.resize_maxHeight = 3E3);
        void 0 === config.resize_minWidth && (config.resize_minWidth = 750);
        void 0 === config.resize_minHeight && (config.resize_minHeight = 250);

        var l = null,
            n, c, h = ('both' == config.resize_dir || 'horizontal' == config.resize_dir) && config.resize_minWidth != config.resize_maxWidth,
            p = ('both' == config.resize_dir || 'vertical' ==
                config.resize_dir) && config.resize_minHeight != config.resize_maxHeight,
            q = CKEDITOR.tools.addFunction(function (d) {
                l || (l = editor.getResizable());
                c = {
                    width: l.$.offsetWidth || 0,
                    height: l.$.offsetHeight || 0
                };
                n = {
                    x: d.screenX,
                    y: d.screenY
                };
                config.resize_minWidth > c.width && (config.resize_minWidth = c.width);
                config.resize_minHeight > c.height && (config.resize_minHeight = c.height);
                CKEDITOR.document.on('mousemove', f);
                CKEDITOR.document.on('mouseup', k);
                editor.document && (editor.document.on('mousemove', f), editor.document.on('mouseup', k));
                d.preventDefault && d.preventDefault();
            });
        editor.on('destroy',
            function () {
                CKEDITOR.tools.removeFunction(q);
            });
        editor.on('uiSpace', function (a) {
            if ("bottom" === a.data.space) {
                var verticalResizer =
                    '\x3cdiv class=\"ckeditor-height-adjuster\"\x3e' +
                        '\x3cdiv title\x3d\"' + CKEDITOR.tools.htmlEncode(editor.lang.common.resize) + '\" class=\"ckeditor-height-adjuster-buttons\" onmousedown\x3d\"CKEDITOR.tools.callFunction(' + q + ', event)\"\x3e' +
                        '\x3c/div\x3e' +
                    '\x3c/div\x3e';

                a.data.html = verticalResizer + a.data.html;
            }
        }, editor, null, 100);
        editor.on("maximize",
            function(a) {
                editor.ui.space("resizer")[a.data == CKEDITOR.TRISTATE_ON ? "hide" : "show"]();
            });
    }
});