
package controller;

import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
import utils.ResponseString;
import static utils.Utils.statusResponse;
import static utils.Validation.validAlias;
import static utils.Validation.validEmail;
import static utils.Validation.validPw;

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
    public Response alterUserStats(
            @FormParam("newAlias") String newName, 
            @FormParam("newEmail") String newEmail, 
            @FormParam("newPw") String newPw, 
            @FormParam("newPw2") String newPw2) {
    
        int ownId = 1; // PLACEHOLDER! Needs to come from the request header!
        User u = uBean.findById(ownId);
        String alias = "noChange";
        String email = "noChange";
        String pw = "noChange";
        
        if (validAlias(newName)) {
            
            u.setAlias(newName);
            alias = newName;
        }
        
        if (validEmail(newEmail)) {
            
            u.setEmail(newEmail);  
            email = newEmail;
        }
                
        if (validPw(newPw) && newPw.equals(newPw2)) {
            
            u.setPw(newPw);   
            pw = newPw;
        }
                        
        uBean.updateDbEntry(u);
    
        ResponseString s = new ResponseString();
        s.add("status", "alteredUserStats");
        s.add("alias", alias);
        s.add("email", email);
        s.add("pw", pw);
        s.pack();
        return Response.ok(s.toString()).build();  
    } // end alterUserStats()
    
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeOwnUser(@QueryParam("removeOwnUser") int id) { // id should be gotten from the request header...
        
        uBean.deleteFromDb(uBean.findById(id));
        
        return statusResponse("removedOwnUser");   
        // TODO: logout (via return value caught in fetch on client) 
    } // end removeOwnUser()
} // end class
