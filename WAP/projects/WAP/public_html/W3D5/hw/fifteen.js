/* global $  */

/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
$(document).ready(function () {
    const rows = 4, cols = 4;
    const shuffleRound = 100, pieceSize = 100;
    let emply;
    init();
    findEmpty();
    findMovable();
    $("#shufflebutton").bind("click", function () {
        for (let i = 0; i < shuffleRound; i++) {
            let index = Math.floor(Math.random() * rows);
            $(".movablepiece:eq(" + index + ")").click();
        }
    });
    /**
     * init puzzle area
     * @returns {undefined}
     */
    function init() {
        let elems = $("#puzzlearea div");
        elems.addClass("puzzlepiece");
        elems.each(function () {
            let num = parseInt($(this).text()) - 1;
            let col = Math.floor(num % cols);
            let row = Math.floor(num / rows);
            let x = col * pieceSize;
            let y = row * pieceSize;
            $(this).css({"left": x + "px", "top": y + "px"});
            $(this).css({"background-position": -x + "px " + -y + "px"});
            $(this).attr({"col": col, "row": row});
        });
    }

    /**
     * locate empty square
     * @returns {undefined}
     */
    function findEmpty() {
        let row = 0, col = 0;
        for (let i = 0; i < rows; i++) {
            if ($(".puzzlepiece[col=" + i + "]").length === rows - 1) {
                col = i;
            }
            if ($(".puzzlepiece[row=" + i + "]").length === rows - 1) {
                row = i;
            }
        }
        emply = {"row": row, "col": col};
    }

    /**
     * locate movable piece
     * @returns {undefined}
     */
    function findMovable() {
        let top = {"row": emply.row - 1, "col": emply.col};
        let left = {"row": emply.row, "col": emply.col - 1};
        let bottom = {"row": emply.row + 1, "col": emply.col};
        let right = {"row": emply.row, "col": emply.col + 1};
        $(".puzzlepiece[col=" + top.col + "][row=" + top.row + "]").addClass("movablepiece");
        $(".puzzlepiece[col=" + left.col + "][row=" + left.row + "]").addClass("movablepiece");
        $(".puzzlepiece[col=" + bottom.col + "][row=" + bottom.row + "]").addClass("movablepiece");
        $(".puzzlepiece[col=" + right.col + "][row=" + right.row + "]").addClass("movablepiece");
        $(".movablepiece").each(function () {
            $(this).bind("click", function () {
                move($(this));
            });
        });
    }

    /**
     * move certain movable piece
     * @param {type} $movablepiece
     * @returns {undefined}
     */
    function move($movablepiece) {
        let x = emply.col * pieceSize;
        let y = emply.row * pieceSize;
        $movablepiece.css({"left": x + "px", "top": y + "px"});
        $movablepiece.attr({"col": emply.col, "row": emply.row});
        $(".movablepiece").unbind("click").removeClass("movablepiece");
        findEmpty();
        findMovable();
    }

});
