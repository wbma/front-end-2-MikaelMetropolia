
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
import static utils.Utils.notNull;
import static utils.Utils.setResponseStatus;
import static utils.Utils.putJson;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * REST Web Service
 * Contains admin-related methods.
 * @author Ville L
 */
@Path("AdminService")
public class AdminService {
    
    @EJB
    private UserBean uBean;

    public AdminService() {
    }
    
    // NOTE: only superadmin can make new admins, so only s/he should be able to see the button by which to grant new admin rights.
    // thus no check for admin status is needed.
    @POST
    @Path("GrantAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject grantAdmin(@FormParam("makeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias); 
        u.setAdmin(1); // only admins may be made like this, not superadmins
        uBean.updateDbEntry(u); // update the db with the change
        
        JSONObject j = new JSONObject();
        putJson(j, "status", "madeAdmin");
        putJson(j, "adminName", alias);
        putJson(j, "adminStatus", u.getAdmin());
        return j;
        // NOTE: theoretically, the superadmin could make themselves a regular admin, but we'll assume they're not that stupid...
    } // end grantAdminRights()
    
    @GET
    @Path("CheckAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject checkAdmin(@QueryParam("checkAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias);  
        
        JSONObject j = new JSONObject();
        putJson(j, "status", "checkAdmin");
        putJson(j, "adminName", alias);
        putJson(j, "adminStatus", u.getAdmin());
        return j;
    }
    
    // NOTE: only superadmin can revoke admin rights, so only s/he should be able to see this button
    @POST
    @Path("RevokeAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject revokeAdmin(@FormParam("revokeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias);    
        u.setAdmin(0);
        uBean.updateDbEntry(u); // update the db with the change
        JSONObject j = new JSONObject();
        putJson(j, "status", "revokedAdmin");
        putJson(j, "adminName", alias);
        putJson(j, "adminStatus", u.getAdmin());
        return j;
    }

    // Meant for admin operations (only admins can see this)
    @POST
    @Path("RemoveAnyUser")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject removeAnyUser(@FormParam("userToRemove") String alias) {
        
        JSONObject json;
        User u = uBean.findByX("Alias", alias);
        
        if (notNull(u)) {
                            
            int id = u.getId();        
            int ownId = 1; // PLACEHOLDER!!! need to get this from the request header!!           
            int ownAdmin = uBean.findById(ownId).getAdmin(); 
                    
            if (u.getId() == ownId) { // delete own user
                
                uBean.deleteFromDb(u);
                json = setResponseStatus("removedOwnUser");
            }
            else if (ownAdmin == 2) { // delete other user as superadmin
                
                uBean.deleteFromDb(u); 
                json = setResponseStatus("removedOtherUser");
            } 
            else { // delete other user as regular admin (regular users cannot see this command, so a check for them is not needed)

                if (u.getAdmin() == 0) {
                
                    uBean.deleteFromDb(u);
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
} // end class
