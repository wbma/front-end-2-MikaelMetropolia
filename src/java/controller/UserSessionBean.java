
package controller;

import javax.ejb.Stateful;
import model.User;

/**
 * Exists to store the 'current user' object for the duration of the session
 * @author Ville L
 */
@Stateful
public class UserSessionBean {

    private User currentUser;
    
    public UserSessionBean() {
        
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        
        return this.currentUser;
    }
    
    public void setCurrentUser(User u) {
        
        this.currentUser = u;
    }
} // end class
