
package controller;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.User;
import static utils.Utils.setResponseStatus;
import org.json.JSONObject;
import static utils.Utils.putJson;

/**
 * REST Web Service
 * Contains user-profile-related methods (to be used by logged-in users).
 * @author Ville L
 */
@Path("ProfileService")
public class ProfileService {

    @EJB
    private UserBean uBean;
    
    public ProfileService() {
    }
       
    @POST
    @Path("ChangeAlias")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject changeAlias(@FormParam("newAlias") String newName) {
        
        // TODO: validate the new name
        // TODO: admin should be able to change anyone's alias, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = uBean.findById(ownId); 
        u.setAlias(newName); // set the new name for the database-stored user...
        uBean.updateDbEntry(u); // ... and update the db with the change
        
         JSONObject j = new JSONObject();
         putJson(j, "status", "loggedInNewAlias");
         putJson(j, "alias", newName);
         return j;
    } // end changeAlias()
    
    @POST
    @Path("ChangeEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject changeEmail(@FormParam("newEmail") String newEmail) {
        
        // TODO: validate the new email
        // TODO: admin should be able to change anyone's email, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = uBean.findById(ownId); 
        u.setEmail(newEmail); // set the new email for the database-stored user...
        uBean.updateDbEntry(u); // ... and update the db with the change
        
        JSONObject j = new JSONObject();
        putJson(j, "status", "loggedInNewEmail");
        putJson(j, "email", newEmail);         
        return j;
    } // end changeEmail()
    
    @POST
    @Path("ChangePw")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject changePw(@FormParam("newPassword") String newPw, @FormParam("newPassword2") String newPw2) {
        
        // TODO: validate the new password. also check that newPw == newPw2
        // TODO: admin should be able to change anyone's pw, not just their own
        
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        
        User u = uBean.findById(ownId); 
        u.setPw(newPw); // set the new email for the database-stored user...
        uBean.updateDbEntry(u); // ... and update the db with the change
        
        JSONObject j = new JSONObject();
        putJson(j, "status", "loggedInNewPw");
        putJson(j, "pw", newPw);         
        return j;
        // TODO: send a msg to the user that they've changed their pw
    } // end changePw()
      
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject removeOwnUser(@QueryParam("removeOwnUser") int id) { // id should be gotten from the request header...
        
        uBean.deleteFromDb(uBean.findById(id)); // delete user from db
        
        return setResponseStatus("removedOwnUser");   
        // TODO: logout (via return value caught in fetch on client) 
    } // end removeOwnUser()
} // end class
