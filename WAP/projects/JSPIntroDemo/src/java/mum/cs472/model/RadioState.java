/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.model;

/**
 *
 * @author toby_
 */
public class RadioState {

    String yesCheck;
    String noCheck;

    public String getYesCheck() {
        return yesCheck;
    }

    public void setYesCheck(String yesCheck) {
        this.yesCheck = yesCheck;
    }

    public String getNoCheck() {
        return noCheck;
    }

    public void setNoCheck(String noCheck) {
        this.noCheck = noCheck;
    }
    
    public void check(String choice){
        if(choice != null){
            if(choice.equals("1")){
                yesCheck = "checked";
            }else{
                noCheck = "checked";
            }
        }
    }
}
