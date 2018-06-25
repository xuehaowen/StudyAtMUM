/* global $ */

/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
$(document).ready(function () {
    /**
     * Move block
     */
//    let position = $("#boundary1").position();
//    let range = [];
//    $(".boundary").each(function () {
//        let p = $(this).position();
//        let top = p.top + 42;
//        let left = p.left;
//        let right = p.left + $(this).width() - 40;
//        let bottom = p.top + $(this).height() + 2;
//        let obj = {left: p.left, right: right, top: p.top, bottom: bottom};
//        range.push(obj);
//        console.log(obj);
//    });
////    console.log(range);
////            trip1: left:0 - 158, top:202 - 208
////            trip2: left:152 - 158, top:52 - 202
////            trip3: left:152 - 306, top:52 - 58
////    trip4:
//    $("#maze").mousemove(function (event) {
//        let left = event.pageX - 530 + "px";
//        let top = event.pageY - 160 + "px";
//        $("#s1").text(position.left + ", " + (position.top + 202));
//        $("#s2").text(left + ", " + top);
//        $("#start").css({left: left, top: top}, "slow");
//    });

    /**
     * Mouse maze
     */
    let isStart = false;
    let isReachWall = false;
    $("#maze .boundary").mouseover(function () {
        fail();
    });
    $("#end").mouseenter(function () {
        checkResult();
    });
    $("#start").click(function () {
        isStart = true;
        isReachWall = false;
        $("#maze .boundary").css({"background-color": "#eeeeee"});
        $("#status").text("Click the \"S\" to begin.");
    });
    $("#maze").mouseleave(function () {
        fail();
        checkResult();
    });
    /**
     * show fail
     * @returns {undefined}
     */
    function fail() {
        if (isStart) {
            $("#maze .boundary").css({"background-color": "red"});
            isReachWall = true;
        }
    }
    /**
     * Check result
     * @returns {undefined}
     */
    function checkResult() {
        if (isStart) {
            let msg;
            if (isReachWall) {
                msg = "Sorry, you lost. :[";
            } else {
                msg = "You win! :]";
            }
            isStart = false;
            $("#status").text(msg);
        }
    }
});
