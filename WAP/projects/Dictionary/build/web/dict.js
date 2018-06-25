/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $("#btn_LookUp").bind("click", function () {
        lookUp();
    });
    function lookUp() {
        $("#div_Consequence p").remove();
        let term = $("#ipt_Term").val().trim();
//        console.log(term);
        $.post("dict.do", {term: term})
                .done(function (msg) {
//                    console.log(msg);
                    let list = $.parseJSON(msg);
                    $.each(list, function (i, elem) {
//                        console.log(elem);
                        let type = elem.type;
                        if (type.length === 0) {
                            type = "";
                        } else {
                            type = "(" + type + ")";
                        }
                        let output = (i + 1) + type + " :: " + elem.definition;
                        $("#div_Consequence").append("<p>" + output + "</p>");
                    });
                })
                .fail(function (xhr, status, error) {
                    console.log(status);
                    console.log(error);
                });
    }
});