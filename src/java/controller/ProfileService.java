
package controller;

import javax.ejb.EJB;
import javax.ws.rs.CookieParam;
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
import static utils.Validation.validPic;

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
    
    // for displaying your own profile stats
    @POST
    @Path("GetUserStats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserStats(@CookieParam("id") int ownId) {
    
        User u = uBean.findById(ownId);
          
        ResponseString s = new ResponseString();
        s.add("status", "gotUserStats");
        s.add("alias", u.getAlias());
        s.add("email", u.getEmail());
        s.add("pw", u.getPw());
        s.add("pic", u.getPic());
        s.add("admin", u.getAdmin()+"");
        s.pack();
        return Response.ok(s.toString()).build();  
    }
    
    @POST
    @Path("AlterUserStats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterUserStats(
            @FormParam("newAlias") String newName, 
            @FormParam("newEmail") String newEmail, 
            @FormParam("newPw") String newPw, 
            @FormParam("newPw2") String newPw2,
            @FormParam("newPic") String newPic,
            @CookieParam("id") int ownId) {
    
        User u = uBean.findById(ownId);
        String alias = "noChange";
        String email = "noChange";
        String pw = "noChange";
        String pic = "noChange";
        
        // TODO: check if user/email/etc already exists !!!!!!!!!!!
        
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
        
        if (validPic(newPic)) {
        
            u.setPic(newPic);
            pic = newPic;
        }
                        
        uBean.updateDbEntry(u);
    
        ResponseString s = new ResponseString();
        s.add("status", "alteredUserStats");
        s.add("alias", alias);
        s.add("email", email);
        s.add("pw", pw);
        s.add("pic", pic);
        s.pack();
        return Response.ok(s.toString()).build();  
    } // end alterUserStats()
    
    @POST
    @Path("RemoveOwnUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeOwnUser(@CookieParam("id") int id) {
        
        uBean.deleteFromDb(uBean.findById(id));
        
        return statusResponse("removedOwnUser");   
        // TODO: logout (via return value caught in fetch on client) 
    } // end removeOwnUser()
} // end class
