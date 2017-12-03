
package utils;

import static utils.Utils.isEmpty;

/**
 * Contains functions for validating various thingies.
 * @author Ville L
 */
public class Validation {
    
    // regExp sure looks neat with those double escapes... let's hope that it works!
    private static final String SPECIALS = "\\!\\$\\&\\%\\+\\#\\\\\\{\\}\\@\\/\\[\\]\\*\\;\\^\\'\\~\\<\\>\\|\\\"\\=\\`\\(\\)";
    
    public static String validAlias(String alias) {
    
        if (isEmpty(alias)) {
            return "aliasEmpty";
        }
        
        if (alias.length() > 20) {
            return "aliasOver20";
        }
        
        // This returns false if the String starts with a number or whitespace, or contains any of a number of illegal characters (in any place)
        if (alias.matches("^(?=.*[\\d\\s+])(?=.*["+SPECIALS+"]+).*$")) {
        
            return "aliasIllegal";
        }
        
        return "aliasValid";
    } // end validAlias()
    
    public static String validEmail(String email) {
    
        if (isEmpty(email)) {
            return "emailEmpty";
        }
        
        if (email.length() > 25) {
            return "emailOver25";
        }
        
        // if the email starts with space or a number or a special character, or contains non-alphabetical, non-numeric characters, 
        // or has an end part with the wrong amount of letters, it is invalid.
        // NOTE: this pattern requires at least two letters for the first part of the email... if some bozo really has an address like 'a@b.com', 
        // s/he can do without our app :D
        if (!email.matches("^[^"+SPECIALS+"\\d\\s+][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,3}$")) {
        
            return "emailIllegal";
        }
     
        return "emailValid";
    } // end validEmail()
    
    public String validPw(String pw) {
        
        if (isEmpty(pw)) {
            return "pwEmpty";
        }
        
        if (pw.length() > 15 || pw.length() < 6) {
            return "pwLengthIllegal";
        }
        
        // password must contain at least 2 special characters, have no whitespace, contain 2 big letters, 2 numbers
        // and 2 small letters
        if (!pw.matches("^(?=.*["+SPECIALS+"]{2,})(?!.*\\s+)(?=.*[a-z]{2,})(?=.*[A-Z]{2,})(?=.*\\d{2,}).*$")) {
        
            return "pwIllegal";
        }
     
        return "pwValid";
    } // end validPw()
    
    public static String validComp() {
    
        return "";
    }
    
        public static String validComment() {
    
        return "";
    }
} // end class
