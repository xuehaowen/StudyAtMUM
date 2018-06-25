/* global ANIMATIONS */

/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
window.onload = function () {
    stop();
    document.getElementById('btn_Start').onclick = setTimer;
    document.getElementById('btn_Stop').onclick = clearTimer;
    document.getElementById('font_size').onchange = setFontSize;
    document.getElementById('cb_speed').onchange = setSpeed;

    let timer = null;
    let text = null;
    let page = 0;
    let act = null;

    const slow = 250;
    const turbo = 50;

    /**
     * When press start
     * @returns {undefined}
     */
    function start() {
        document.getElementById('btn_Stop').disabled = false;
        document.getElementById('btn_Start').disabled = true;
        document.getElementById('animation_name').disabled = true;
    }

    /**
     * When press stop
     * @returns {undefined}
     */
    function stop() {
        document.getElementById('btn_Stop').disabled = true;
        document.getElementById('btn_Start').disabled = false;
        document.getElementById('animation_name').disabled = false;
    }

    /**
     * setTimer based on speed
     * @returns {undefined}
     */
    function setTimer() {
        start();
        let speed = getSpeed();
        if (timer === null) {
            text = document.getElementById('tA').value;
            act = getAnimation();
            page = 0;
        } else {
            clearInterval(timer);
            timer = null;
        }
        timer = setInterval(showAnimation, speed);
        /**
         * active animation
         * @returns {undefined}
         */
        function showAnimation() {
            if (page >= act.length) {
                page = 0;
            }
            document.getElementById('tA').value = act[page];
            page++;
        }

    }

    /**
     * clear timer
     * @returns {undefined}
     */
    function clearTimer() {
        stop();
        if (timer !== null) {
            clearInterval(timer);
            timer = null;
            document.getElementById('tA').value = text;
        }
    }

    /**
     * get animation array
     * @returns {unresolved}
     */
    function getAnimation() {
        let choice = document.getElementById('animation_name').value;
        return ANIMATIONS[choice].split('=====\n');
    }

    /**
     * set font size
     * @returns {undefined}
     */
    function setFontSize() {
        let size = document.getElementById('font_size').value;
        document.getElementById('tA').style.fontSize = size;
    }

    /**
     * set speed
     * @returns {undefined}
     */
    function setSpeed() {
        if (timer !== null) {
            setTimer();
        }
    }

    /**
     * get speed
     * @returns {Number}
     */
    function getSpeed() {
        let isTurbo = document.getElementById('cb_speed').checked;
        if (isTurbo) {
            return turbo;
        } else {
            return slow;
        }
    }
};

