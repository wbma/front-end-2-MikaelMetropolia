
package controller;

import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
import static utils.Utils.notNull;
import static utils.Utils.statusResponse;
import org.json.JSONObject;
import utils.ResponseString;

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
    public Response grantAdmin(@FormParam("makeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias); 
        u.setAdmin(1); // only admins may be made like this, not superadmins
        uBean.updateDbEntry(u); // update the db with the change
        
        ResponseString s = new ResponseString();
        s.add("status", "madeAdmin");
        s.add("adminName", alias);
        s.add("adminStatus", u.getAdmin()+"");
        s.pack();
        return Response.ok(s.toString()).build();
        // NOTE: theoretically, the superadmin could make themselves a regular admin, but we'll assume they're not that stupid...
    } // end grantAdminRights()
    
    @GET
    @Path("CheckAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAdmin(@QueryParam("checkAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias);  
        
        ResponseString s = new ResponseString();
        s.add("status", "checkAdmin");
        s.add("adminName", alias);
        s.add("adminStatus", u.getAdmin()+"");
        s.pack();
        return Response.ok(s.toString()).build();
    }
    
    // NOTE: only superadmin can revoke admin rights, so only s/he should be able to see this button
    @POST
    @Path("RevokeAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response revokeAdmin(@FormParam("revokeAdmin") String alias) {
        
        // TODO: see that the user exists
        
        User u = uBean.findByX("Alias", alias);    
        u.setAdmin(0);
        uBean.updateDbEntry(u); // update the db with the change
        
        ResponseString s = new ResponseString();
        s.add("status", "revokedAdmin");
        s.add("adminName", alias);
        s.add("adminStatus", u.getAdmin()+"");
        return Response.ok(s.toString()).build();
    }

    // Meant for admin operations (only admins can see this)
    @POST
    @Path("RemoveAnyUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAnyUser(@FormParam("userToRemove") String alias) {
        
        JSONObject json;
        User u = uBean.findByX("Alias", alias);
        
        if (notNull(u)) {
                            
            int id = u.getId();        
            int ownId = 1; // PLACEHOLDER!!! need to get this from the request header!!           
            int ownAdmin = uBean.findById(ownId).getAdmin(); 
                    
            if (u.getId() == ownId) { // delete own user
                
                uBean.deleteFromDb(u);
                return statusResponse("removedOwnUser");
            }
            else if (ownAdmin == 2) { // delete other user as superadmin
                
                uBean.deleteFromDb(u); 
                return statusResponse("removedOtherUser");
            } 
            else { // delete other user as regular admin (regular users cannot see this command, so a check for them is not needed)

                if (u.getAdmin() == 0) {
                
                    uBean.deleteFromDb(u);
                    return statusResponse("removedOtherUser");
                
                } else {
                
                    return statusResponse("deniedToRemoveOtherAdmin");                
                }
            } // end admin status else-if      
        } 
        else {
        
            return statusResponse("failedToRemoveInvalidUser");
        } // end nullcheck else-if
    } // end removeAnyUSer()
} // end class
