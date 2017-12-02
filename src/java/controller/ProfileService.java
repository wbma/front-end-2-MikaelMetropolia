
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
    @Path("AlterUserStats")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject alterUserStats(
            @FormParam("newAlias") String newName, 
            @FormParam("newEmail") String newEmail, 
            @FormParam("newPw") String newPw, 
            @FormParam("newPw2") String newPw2) {
    
        // TODO: validate the new stat(s)
    
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        User u = uBean.findById(ownId);
        String alias = "noChange";
        String email = "noChange";
        String pw = "noChange";
                
        if (!newName.matches("\\s*")) {
            u.setAlias(newName);
            uBean.updateDbEntry(u);
            alias = newName;
        }
        
        if (!newEmail.matches("\\s*")) {
            u.setEmail(newEmail); 
            uBean.updateDbEntry(u); 
            email = newEmail;
        }
                
        if (!newPw.matches("\\s*")) {
            u.setPw(newPw);
            uBean.updateDbEntry(u);
            pw = newPw;
        }
    
        JSONObject j = new JSONObject();
        putJson(j, "status", "alteredUserStats");
        putJson(j, "alias", alias);
        putJson(j, "email", email);
        putJson(j, "pw", pw);
        return j;
    } // end alterUserStats()
    
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject removeOwnUser(@QueryParam("removeOwnUser") int id) { // id should be gotten from the request header...
        
        uBean.deleteFromDb(uBean.findById(id)); // delete user from db
        
        return setResponseStatus("removedOwnUser");   
        // TODO: logout (via return value caught in fetch on client) 
    } // end removeOwnUser()
} // end class
