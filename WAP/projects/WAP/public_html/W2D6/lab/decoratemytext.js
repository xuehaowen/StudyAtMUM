/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
//    document.getElementById('btn_BD').onclick = showMsg;
    document.getElementById('tA').style.fontSize = "12pt";
    document.getElementById('btn_BD').onclick = setTimer;
    document.getElementById('btn_CB').onchange = showMsg;
    document.getElementById('btn_IA').onclick = igpay_Atinlay;
    document.getElementById('btn_MK').onclick = malkovich;
};

function showMsg() {
    alert("Hello, world!");
    var ele = document.getElementById('btn_CB');
    if (ele.checked) {
        document.getElementById('tA').style.fontWeight = "bold";
        document.getElementById('tA').style.color = "green";
        document.getElementById('tA').style.textDecoration = "underline";
        document.getElementsByTagName('body')[0].style.backgroundImage = "url('http://www.cs.washington.edu/education/courses/190m/CurrentQtr/labs/6/hundred-dollar-bill.jpg')";
    } else {
        document.getElementById('tA').style.fontWeight = "normal";
        document.getElementById('tA').style.color = "black";
        document.getElementById('tA').style.textDecoration = "none";
        document.getElementsByTagName('body')[0].style.backgroundImage = "none";
    }
}
var timer = null;

function setTimer() {
    if (timer === null) {
        timer = setInterval(bigger, 500);
    } else {
        clearInterval(timer);
        timer = null;
    }

}

function bigger() {
//    document.getElementById('tA').style.fontSize="24pt";
    var ele = document.getElementById('tA');
    var fontSize = parseInt(ele.style.fontSize);
    fontSize += 2;
//    alert(fontSize);
    ele.style.fontSize = fontSize + "pt";
}

function igpay_Atinlay() {
    var elem = document.getElementById('tA');
    var value = elem.value.trim().split(' ');
    for (var i = 0; i < value.length; i++) {
        value[i] = check(value[i]);
    }
    var res = "";
    for (var i = 0; i < value.length; i++) {
        res += value[i]+" ";
    }
    elem.value = res.trim();
//    alert(value);

}

function check(word) {
    var patt = /^[AEIOU]/i;
    if (patt.test(word)) {
        return word + "ay";
    } else {
        var patt2 = /^[^AEIOU]{1,}/i;
        var cc = patt2.exec(word);
//        alert(cc);
        var res = word.substr(cc.length);
        return res + cc + "ay";
    }
}

function malkovich() {
    var elem = document.getElementById('tA');
    var value = elem.value.trim();
    var res = value.replace(/[a-z]{5,}/ig, "Malkovich");
    elem.value = res;
}