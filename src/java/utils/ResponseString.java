/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Making my own String object to pass to Response.ok() in the services... It doesn't seem to play nice with standard JSONObjects.
 * @author Ville L
 */
public class ResponseString {
    
    private String str;
    
    public ResponseString() {
    
        this.str = "";
    }
    
    public void add(String key, String value) {
    
        this.str = this.str + " \"" + key + "\": " + "\"" + value + "\",";
    }
    
    public void pack() {
    
        this.str = this.str.substring(0, this.str.length()-1); // remove last, superfluous comma
        this.str = "{" + this.str + " }"; // add brackets around the whole thing
    }
    
    @Override
    public String toString() {
    
        return this.str;
    }
    
} // end class
