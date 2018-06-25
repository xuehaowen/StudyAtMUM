/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var rudyTimer = (function () {
    timer = null; // stores ID of interval timer
    function rudy() { // called each time the timer goes
        document.getElementById("output").innerHTML += " Rudy!";
    }
    return function () {
        if (timer === null) {
            timer = setInterval(rudy, 1000);
        } else {
            clearInterval(timer);
            timer = null;
        }
    }
})();



