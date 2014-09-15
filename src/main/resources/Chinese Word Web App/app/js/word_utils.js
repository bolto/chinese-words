'use strict';

var chinesewordCommonUtils = angular.module('chinesewordCommonUtils', []);

/* Common codes */
chinesewordCommonUtils.factory('WordUtils', ['PingyingCharacter', 'Tone', function (PingyingCharacter, Tone) {
    var root = {};
    root.pingying_character_lut = {};
    root.pingying_character_lut[1] = 'ㄅ';
    root.pingying_character_lut[2] = 'ㄆ';
    root.pingying_character_lut[3] = 'ㄇ';
    root.pingying_character_lut[4] = 'ㄈ';
    root.pingying_character_lut[5] = 'ㄉ';
    root.pingying_character_lut[6] = 'ㄊ';
    root.pingying_character_lut[7] = 'ㄋ';
    root.pingying_character_lut[8] = 'ㄌ';
    root.pingying_character_lut[9] = 'ㄍ';
    root.pingying_character_lut[10] = 'ㄎ';
    root.pingying_character_lut[11] = 'ㄏ';
    root.pingying_character_lut[12] = 'ㄐ';
    root.pingying_character_lut[13] = 'ㄑ';
    root.pingying_character_lut[14] = 'ㄒ';
    root.pingying_character_lut[15] = 'ㄓ';
    root.pingying_character_lut[16] = 'ㄔ';
    root.pingying_character_lut[17] = 'ㄕ';
    root.pingying_character_lut[18] = 'ㄖ';
    root.pingying_character_lut[19] = 'ㄗ';
    root.pingying_character_lut[20] = 'ㄘ';
    root.pingying_character_lut[21] = 'ㄙ';
    root.pingying_character_lut[22] = 'ㄧ';
    root.pingying_character_lut[23] = 'ㄨ';
    root.pingying_character_lut[24] = 'ㄩ';
    root.pingying_character_lut[25] = 'ㄚ';
    root.pingying_character_lut[26] = 'ㄛ';
    root.pingying_character_lut[27] = 'ㄜ';
    root.pingying_character_lut[28] = 'ㄝ';
    root.pingying_character_lut[29] = 'ㄞ';
    root.pingying_character_lut[30] = 'ㄟ';
    root.pingying_character_lut[31] = 'ㄠ';
    root.pingying_character_lut[32] = 'ㄡ';
    root.pingying_character_lut[33] = 'ㄢ';
    root.pingying_character_lut[34] = 'ㄣ';
    root.pingying_character_lut[35] = 'ㄤ';
    root.pingying_character_lut[36] = 'ㄥ';
    root.pingying_character_lut[37] = 'ㄦ';
    root.pingying_character_lut[38] = '';
    root.tone_lut = {};
    root.tone_lut[1] = '';
    root.tone_lut[2] = 'ˊ';
    root.tone_lut[3] = 'ˇ';
    root.tone_lut[4] = 'ˋ';
    root.tone_lut[5] = '˙';
    root.FormatStyle = {
        STANDARD: 1,
        EDIT: 2
    };
    root.toFormattedWord = function toFormattedWord(word, formatStyle){
        var fw = new Object();
        fw.letter = word.symbol;
        if(root.hasPingying(word)){
            switch(formatStyle){
                case root.FormatStyle.STANDARD:
                    fw.py = root.toFormattedPingying(word.wordPingyingId.pingying);
                    break;
                case root.FormatStyle.EDIT:
                    fw.py = root.toFormattedPingyingForEdit(word.wordPingyingId.pingying);
                    break;
                default:
                    break;
            }
        }

        return fw;
    }
    root.hasPingying = function hasPingying(word){
        return !(typeof word.wordPingyingId == 'undefined' || word.wordPingyingId == null);
    }
    root.toFormattedPingyingStr = function toFormattedPingyingStr(py){
        var str = '';
        str += root.pingying_character_lut[py.firstPyId];
        str += root.pingying_character_lut[py.secondPyId];
        str += root.pingying_character_lut[py.thirdPyId];
        str += root.tone_lut[py.toneId];
        return str;
    }
    root.toFormattedPingying = function toFormattedPingying(py){
        if(py == null)
            return null;
        var py_str = '';
        var tone_str = '';
        if(py.firstPyId != 38){
            py_str += root.pingying_character_lut[py.firstPyId];
        }
        if(py.secondPyId != 38){
            py_str += root.pingying_character_lut[py.secondPyId];
        }
        if(py.thirdPyId != 38){
            py_str += root.pingying_character_lut[py.thirdPyId];
        }
        tone_str += root.tone_lut[py.toneId];
        //alert("pys: " + py_str + ".  tone:" + tone_str);

        var fpy = new Object();
        fpy.one = '';
        //fpy.toneNextToTwo = '';
        fpy.two = '';
        fpy.toneBoldAtTwo = '';
        fpy.three = '';
        fpy.four = '';
        fpy.tone = '';
        if(py_str.length == 1){
            fpy.three = py_str;
            if(py.toneId == 5){
                fpy.twoBold = tone_str;
            }else if(py.toneId != 1){
                fpy.tone = tone_str;
            }
        }else if(py_str.length == 2){
            fpy.two = py_str[0];
            fpy.three = py_str[1];
            if(py.toneId == 5){
                fpy.one = tone_str;
            }else if(py.toneId != 1){
                fpy.tone = tone_str;
            }
        }else if(py_str.length == 3){
            fpy.two = py_str[0];
            fpy.three = py_str[1];
            fpy.four = py_str[2];
            if(py.toneId == 5){
                fpy.one = tone_str;
            }else if(py.toneId != 1){
                fpy.tone = tone_str;
            }
        }
        return fpy;
    }
    root.toFormattedPingyingForEdit = function toFormattedPingyingForEdit(py){
        if(py == null)
            return null;
        var py_str = '';
        var tone_str = '';
        if(py.firstPyId != 38){
            py_str += root.pingying_character_lut[py.firstPyId];
        }
        if(py.secondPyId != 38){
            py_str += root.pingying_character_lut[py.secondPyId];
        }
        if(py.thirdPyId != 38){
            py_str += root.pingying_character_lut[py.thirdPyId];
        }
        tone_str += root.tone_lut[py.toneId];
        //alert("pys: " + py_str + ".  tone:" + tone_str);

        var fpy = new Object();
        fpy.one = '';
        //fpy.toneNextToTwo = '';
        fpy.two = '';
        fpy.toneBoldAtTwo = '';
        fpy.three = '';
        fpy.four = '';
        fpy.tone = '';
        if(py_str.length == 1){
            fpy.two = py_str;
            if(py.toneId == 5){
                fpy.toneBoldAtTwo = tone_str;
            }else if(py.toneId != 1){
                fpy.toneNextToTwo = tone_str;
            }
        }else if(py_str.length == 2){
            fpy.two = py_str[0];
            fpy.three = py_str[1];
            if(py.toneId == 5){
                fpy.one = tone_str;
            }else if(py.toneId != 1){
                fpy.tone = tone_str;
            }
        }else if(py_str.length == 3){
            fpy.two = py_str[0];
            fpy.three = py_str[1];
            fpy.four = py_str[2];
            if(py.toneId == 5){
                fpy.one = tone_str;
            }else if(py.toneId != 1){
                fpy.tone = tone_str;
            }
        }
        return fpy;
    }
    return root;
}]);