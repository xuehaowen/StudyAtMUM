/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {
    document.getElementById('submit').onclick = Factory.createAc;
};

var actList = [];

var Factory = (function () {
    function Account(name, balance) {
        this.name = name;
        this.balance = balance;
    }
    function reFresh() {
        document.getElementById('tA').innerHTML = "";
        for (i = 0; i < actList.length; i++) {
            var output = "Account name: " + actList[i].name + ". Balance: " + actList[i].balance + "\n";
            document.getElementById('tA').innerHTML += output;
        }
    }
    return {
        createAc: function () {
            var name = document.getElementById('ipt_act_name').value.trim();
            var balance = document.getElementById('ipt_act_balance').value.trim();
//            console.log("input name:" + name);
//            console.log("input balance:" + balance);
            var act = new Account(name, balance);
//            console.log(act);
            actList.push(act);
//            console.log(actList);
            reFresh();
        }

    };
}());
