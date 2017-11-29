
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
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
    
    public RestfulDbService() {
    }
    
    // what is this supposed to do ??
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    // ===========================================================================================================================
    // USER-TABLE METHODS
    
    // get all User database entries
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        
        return dbBean.getAllUsers();
    }
    
    // fetch form data from the signup form and try to make a new user with it
    @POST
    @Path("SignUp")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
    public JsonObject signUp(@FormParam("email") String email, @FormParam("username") String name, @FormParam("password") String pw, @FormParam("password2") String pw2) {
    
        // TODO: call some method to validate the data (email format, pw == pw2, etc)
        
        JsonObject json;
        
        if (notNull(dbBean.findByX("Alias", name))) { // username taken
            
            json = setResponseStatus("usernameTaken");     
            // TODO: notify user on client with this info
        } 
        else if (notNull(dbBean.findByX("Email", email))) { // email taken
        
            json = setResponseStatus("emailTaken"); 
            // TODO: notify user on client with this info
        } 
        else { // email and username are both available
        
            User u = new User();
            u.setEmail(email);
            u.setAlias(name);
            u.setPw(pw);
            u.setAdmin(0); // 0 by default; 0 = regular user, 1 = admin, 2 = superadmin. (admins can only be added manually through MariaDB)
            dbBean.insertToDb(u);
            
            json = Json.createObjectBuilder()
            .add("status", "loggedIn")
            .add("id", dbBean.findByX("Alias", name).getId())
            .add("email", email)
            .add("alias", name)
            .add("admin", 0)
            .build();              
            // TODO: on the client, since you are now registered and logged in, alter the login/register page to contain only 'logout' button
            // TODO: get the id on client-side and store it in a cookie to maintain session state 
        }     
        return json;   
    } // end signUp()
    
    // fetch form data from the login form and try to log in with an existing user
    @POST
    @Path("LogIn")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
    public JsonObject logIn(@FormParam("loginUsername") String name, @FormParam("loginPassword") String pw) {
    
        // TODO: call method that validates form arguments
        JsonObject json;
        User u = dbBean.findByX("Alias", name); // check user existence by name
        
        if (notNull(u) && u.getPw().equals(pw)) {
            
            // TODO: since you are now logged in, alter the login/register page to contain only 'logout' button
            
            json = Json.createObjectBuilder()
            .add("status", "loggedIn") 
            .add("id", u.getId())
            .add("email", u.getEmail())
            .add("alias", name) // would work with u.getName() as well
            .add("admin", u.getAdmin())
            .build();
        } 
        else if (notNull(u) && !(u.getPw().equals(pw))) {
        
            json = setResponseStatus("wrongPw"); 
        } 
        else {    
             json = setResponseStatus("wrongUsername");       
        }
        return json;
    } // end logIn()
    
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject removeOwnUser(@QueryParam("removeOwnUser") int id) { // id should be gotten from the request header... how to do it?
        
        dbBean.deleteFromDb(dbBean.findById(id)); // delete user from db
        
        return setResponseStatus("removedOwnUser");   
        // TODO: logout (via return value caught in fetch on client) 
    } // end removeOwnUser()
    
    // Meant for admin operations (only admins can see this)
    @POST
    @Path("RemoveAnyUser")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject removeAnyUser(@FormParam("userToRemove") String alias) {
        
        JsonObject json;
        User u = dbBean.findByX("Alias", alias);
        
        if (notNull(u)) {
                            
            int id = u.getId();        
            int ownId = 1; // PLACEHOLDER!!! need to get this from the request header!!           
            int ownAdmin = dbBean.findById(ownId).getAdmin(); 
                    
            if (u.getId() == ownId) { // delete own user
                
                dbBean.deleteFromDb(u);
                json = setResponseStatus("removedOwnUser");
            }
            else if (ownAdmin == 2) { // delete other user as superadmin
                
                dbBean.deleteFromDb(u); 
                json = setResponseStatus("removedOtherUser");
            } 
            else { // delete other user as regular admin (regular users cannot see this command, so a check for them is not needed)

                if (u.getAdmin() == 0) {
                
                    dbBean.deleteFromDb(u);
                    json = setResponseStatus("removedOtherUser");
                
                } else {
                
                    json = setResponseStatus("deniedToRemoveOtherAdmin");                
                }
            } // end admin status else-if      
        } 
        else {
        
            json = setResponseStatus("failedToRemoveInvalidUser");
        } // end nullcheck else-if
        
        return json;
    } // end removeAnyUSer()
    
    @POST
    @Path("ChangeAlias")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject changeAlias(@FormParam("newAlias") String newName) {
        
        // TODO: validate the new name
        // TODO: admin should be able to change anyone's alias, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = dbBean.findById(ownId); 
        u.setAlias(newName); // set the new name for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        
         return Json.createObjectBuilder()
        .add("status", "loggedInNewAlias")
        .add("alias", newName) // would work with u.getName() as well
        .build();
    } // end changeAlias()
    
    @POST
    @Path("ChangeEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject changeEmail(@FormParam("newEmail") String newEmail) {
        
        // TODO: validate the new email
        // TODO: admin should be able to change anyone's email, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = dbBean.findById(ownId); 
        u.setEmail(newEmail); // set the new email for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        
         return Json.createObjectBuilder()
        .add("status", "loggedInNewEmail")
        .add("email", newEmail) // would work with u.getEmail() as well
        .build();
    } // end changeEmail()
    
    @POST
    @Path("ChangePw")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject changePw(@FormParam("newPassword") String newPw, @FormParam("newPassword2") String newPw2) {
        
        // TODO: validate the new password. also check that newPw == newPw2
        // TODO: admin should be able to change anyone's pw, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = dbBean.findById(ownId); 
        u.setPw(newPw); // set the new email for the database-stored user...
        dbBean.updateDbEntry(u); // ... and update the db with the change
        
         return Json.createObjectBuilder()
        .add("status", "loggedInNewPw")
        .add("pw", newPw) // would work with u.getPw() as well
        .build();
        // TODO: send a msg to the user that they've changed their pw
    } // end changePw()
    
    // NOTE: only superadmin can make new admins, so only s/he should be able to see the button by which to grant new admin rights.
    // thus no check for admin status is needed.
    @POST
    @Path("GrantAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject grantAdmin(@FormParam("makeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias); 
        u.setAdmin(1); // only admins may be made like this, not superadmins
        dbBean.updateDbEntry(u); // update the db with the change
        
        return Json.createObjectBuilder()
        .add("status", "madeAdmin")
        .add("adminName", alias)
        .add("adminStatus", u.getAdmin())
        .build();
        // NOTE: theoretically, the superadmin could make themselves a regular admin, but we'll assume they're not that stupid...
    } // end grantAdminRights()
    
    @GET
    @Path("CheckAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject checkAdmin(@QueryParam("checkAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias);    
        return Json.createObjectBuilder()
        .add("status", "checkAdmin")
        .add("adminName", alias)
        .add("adminStatus", u.getAdmin())        
        .build();
    }
    
    // NOTE: only superadmin can revoke admin rights, so only s/he should be able to see this button
    @POST
    @Path("RevokeAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject revokeAdmin(@FormParam("revokeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = dbBean.findByX("Alias", alias);    
        u.setAdmin(0);
        dbBean.updateDbEntry(u); // update the db with the change
        return Json.createObjectBuilder()
        .add("status", "revokedAdmin")
        .add("adminName", alias)
        .add("adminStatus", u.getAdmin())
        .build();
    }
    

    
    // ===========================================================================================================================
    // COMPOSITION-TABLE methods
    
    
    
    
    
    
    
    
    // ===========================================================================================================================
    // HELPER METHODS (etc)
    // NOTE: move to their own file..?
    
    // helper method to convert 'findByX()' call result to boolean form
    private boolean notNull(Object o) {
    
        return !(o == null);
    }
    
    // helper method to easily set a single-line status field for a json (response) object
    private JsonObject setResponseStatus(String statusMsg) {
        
        return Json.createObjectBuilder()
       .add("status", statusMsg)
       .build();
    }
    
    
} // end class
