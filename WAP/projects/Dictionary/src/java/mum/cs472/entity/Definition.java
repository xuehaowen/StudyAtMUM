/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.entity;

/**
 *
 * @author toby_
 */
public class Definition {
    
    private String word;
    private String type;
    private String definition;

    @Override
    public String toString() {
        return "Definition{" + "word=" + word + ", type=" + type + ", definition=" + definition + '}';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
