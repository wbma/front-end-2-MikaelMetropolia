
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.User;

/**
 * REST Web Service. Exposed to the outside world (point of communication). Tied to form fields via @FormParam
 * @author Ville L
 */
@Path("RestfulDbService")
public class RestfulDbService {

    @EJB
    private DatabaseBean dbBean;
    
    @EJB
    private UserSessionBean userBean; // complains about duplicate annotation... not sure what's correct here!

    public RestfulDbService() {
    }
    
    // what is this supposed to do ??
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    // ===========================================================================================================================
    // USER-RELATED METHODS
    
    // get all User database entries
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        
        return dbBean.getAllUsers();
    }
    
    // fetch form data from the signup form and try to make a new user with it
    @POST
    @Path("SignUp")
    @Produces(MediaType.APPLICATION_JSON) 
    public User signUp(@FormParam("email") String email, @FormParam("username") String name, @FormParam("password") String pw, @FormParam("password2") String pw2) {
    
        // TODO: call some method to validate the data (email format, pw == pw2, etc)
        
        if (notNull(dbBean.findByX("Alias", name))) { // username taken
        
            // TODO: notify user
            return null;
        } 
        else if (notNull(dbBean.findByX("Email", email))) { // email taken
        
            // TODO: notify user
            return null;
        } 
        else { // email and username are both available
        
            User u = new User();
            u.setEmail(email);
            u.setAlias(name);
            u.setPw(pw);
            u.setAdmin(0); // 0 by default; 0 = regular user, 1 = admin, 2 = superadmin
            userBean.setCurrentUser(u);
            return dbBean.insertToDb(u);
            // TODO: since you are now registerd and logged in, alter the login/register page to contain only 'logout' button
        }
    } // end signUp()
    
    // fetch form data from the login form and try to log in with an existing user
    @POST
    @Path("LogIn")
    @Produces(MediaType.APPLICATION_JSON) 
    public User logIn(@FormParam("loginUsername") String name, @FormParam("loginPassword") String pw) {
    
        // TODO: call method that validates form arguments
        
        User u = dbBean.findByX("Alias", name); // check user existence by name
        
        if (notNull(u) && (u.getPw().equals(pw))) {
            
            // TODO: send the result somewhere. New stateful SessionBean ?? currentUser = u; ??
            // TODO: since you are now logged in, alter the login/register page to contain only 'logout' button
            userBean.setCurrentUser(u);
            return u; // correct username and pw    
        } 
        else if (notNull(u) && !(u.getPw().equals(pw))) {
        
            // wrong password; notify user (TODO)
            return null;
        } 
        else {    
            // wrong username; notify user (TODO)
            return null;
        }
    } // end logIn()
    
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeOwnUser(@FormParam("removeOwnUser") int id) { // how do we call it without parameters? Without them, only 'POST' can be specified
        
        // TODO: logout. HOW ??
        // TODO: send a msg to the user that they've removed their account   
        dbBean.deleteFromDb(userBean.getCurrentUser()); // delete user from db
        userBean.setCurrentUser(null); // remove user from session 'memory'.... hopefully
    }
    
    @POST
    @Path("RemoveAnyUser")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeAnyUser(@FormParam("removeThisUser") String alias) {
           
        dbBean.deleteFromDb(dbBean.findByX("Alias", alias)); // delete user from db
        // TODO: restrict deletion rights by admin status (superadmin can delete anyone, admins only regular users)
        
        if (alias.equals(userBean.getCurrentUser().getAlias())) { // if it's own user

            userBean.setCurrentUser(null); // remove user from session 'memory'.... hopefully
            // TODO: logout if it's your own user. HOW ??
            // TODO: send a msg to the user that they've removed their account (if it's their own acc)
        }
    }
    
    @POST
    @Path("ChangeAlias")
    @Produces(MediaType.APPLICATION_JSON)
    public User changeAlias(@FormParam("newAlias") String newName) {
        
        // TODO: validate the new name
        
        User u = dbBean.findById(userBean.getCurrentUser().getId()); 
        u.setAlias(newName); // set the new name for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        userBean.getCurrentUser().setAlias(newName); // set the new name also for the session-specific currentUser
        return u;
        // TODO: send a msg to the user that they've renamed their user
    }
    
    @POST
    @Path("ChangeEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public User changeEmail(@FormParam("newEmail") String newEmail) {
        
        // TODO: validate the new email
        
        User u = dbBean.findById(userBean.getCurrentUser().getId()); 
        u.setEmail(newEmail); // set the new email for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        userBean.getCurrentUser().setEmail(newEmail); // set the new email also for the session-specific currentUser
        return u;
        // TODO: send a msg to the user that they've changed their email
    }
    
    @POST
    @Path("ChangePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public User changePassword(@FormParam("newPassword") String newPw, @FormParam("newPassword2") String newPw2) {
        
        // TODO: validate the new password. also check that newPw == newPw2
        
        User u = dbBean.findById(userBean.getCurrentUser().getId()); 
        u.setEmail(newPw); // set the new password for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        userBean.getCurrentUser().setPw(newPw); // set the new pw also for the session-specific currentUser
        return u;
        // TODO: send a msg to the user that they've changed their pw
    }
    
    // NOTE: only superadmin can make new admins, so only s/he should see the button by which to grant new admin rights
    @POST
    @Path("GrantAdminRights")
    @Produces(MediaType.APPLICATION_JSON)
    public User grantAdminRights(@FormParam("makeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias); 
        u.setAdmin(1); // only admins may be made like this, not superadmins
        dbBean.updateDbEntry(u); // update the db with the change
        return u;
        // TODO: send a msg to the user that they've changed the admin rights
        // NOTE: theoretically, the superadmin might make themselves a regular admin, but we'll assume they're not that stupid...
    } // end grantAdminRights()
    
    @GET
    @Path("CheckAdminRights")
    @Produces(MediaType.APPLICATION_JSON)
    public int checkAdminRights(@QueryParam("checkAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias);    
        return u.getAdmin();
    }
    
    // NOTE: only superadmin can revoke admin rights, so only s/he should see this button
    @POST
    @Path("RevokeAdminRights")
    @Produces(MediaType.APPLICATION_JSON)
    public User revokeAdminRights(@QueryParam("revokeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias);    
        u.setAdmin(0);
        dbBean.updateDbEntry(u); // update the db with the change
        return u;
    }
    
    // This shouldn't be needed... It's more of an internal operation to find users by id.
    // Candidate for deletion if we don't find some use for it
    @GET
    @Path("GetUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@QueryParam("id") int id) {
        
        User u = dbBean.findById(id);
        return u;
    }
    
    
    // ===========================================================================================================================
    // HELPER METHODS (etc)
    
    // helper method to convert 'findByX()' call result to boolean form
    public boolean notNull(Object o) {
    
        return !(o == null);
    }
    
    
}
