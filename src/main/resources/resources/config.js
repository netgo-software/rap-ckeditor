/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';

    config.skin = 'moono-lisa';
    config.ignoreEmptyParagraph = true;
    config.htmlEncodeOutput = true;

    config.toolbarGroups = [
        { name: 'tools' },
        { name: 'clipboard', groups: ['clipboard', 'undo'] },
        { name: 'insert' },
        { name: 'others' },
        { name: 'basicstyles', groups: ['basicstyles', 'cleanup'] },
        { name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align'] },
        { name: 'styles' },
        { name: 'colors' },
        { name: 'verticalresizer' },
        { name: 'dinavier' }
    ];

    config.disableNativeSpellChecker = false;
    config.removeButtons = 'ShowBlocks,Image,Flash,Smiley,PageBreak,Iframe,CopyFormatting,Styles,CreateDiv';

    // Se the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    config.font_defaultLabel = 'Arial';

    config.fontSize_defaultLabel = '10';
    config.fontSize_sizes = '8/8pt;9/9pt;10/10pt;11/11pt;12/12pt;14/14pt;16/16pt;18/18pt;20/20pt;22/22pt;24/24pt;26/26pt;28/28pt;36/36pt;48/48pt;72/72pt';

    // Make dialogs simpler. 
    config.removeDialogTabs = 'image:advanced;link:advanced;tableProperties:advanced';

    config.extraPlugins = 'onchange,font,liststyle,dialog,dialogui,clipboard,pastefromword,colorbutton,justify,list,indent,indentlist,indentblock,showborders,dinavier,copyformatting,verticalresizer';

    config.allowedContent = true;

    config.toolbarCanCollapse = true;
    config.toolbarStartupExpanded = true;

    config.removePlugins = 'elementspath';
    config.resize_enabled = false;

    config.pasteFromWordRemoveFontStyles = false;
    config.pasteFromWordRemoveStyles = false;
};