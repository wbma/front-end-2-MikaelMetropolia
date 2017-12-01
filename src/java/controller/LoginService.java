
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.User;
import static utils.Utils.notNull;
import static utils.Utils.setResponseStatus;
import org.json.JSONObject;
import static utils.Utils.putJson;

/**
 * REST Web Service.
 * Contains login / signup-related methods.
 * @author Ville L
 */
@Path("UserService")
public class LoginService {

    @EJB
    private UserBean uBean;
    
    public LoginService() {
    }
    
    // get all entries from the 'User' table
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        
        return uBean.getAllUsers();
    }
    
    // fetch form data from the signup form and try to make a new user with it
    @POST
    @Path("SignUp")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject signUp(
            @FormParam("email") String email, 
            @FormParam("username") String name, 
            @FormParam("password") String pw, 
            @FormParam("password2") String pw2) {
    
        // TODO: call some method to validate the data (email format, pw == pw2, etc)
        
        JSONObject json;
        
        if (notNull(uBean.findByX("Alias", name))) { // username taken
            
            json = setResponseStatus("usernameTaken");     
            // TODO: notify user on client with this info
        } 
        else if (notNull(uBean.findByX("Email", email))) { // email taken
        
            json = setResponseStatus("emailTaken"); 
            // TODO: notify user on client with this info
        } 
        else { // email and username are both available        
            User u = new User();
            u.setEmail(email);
            u.setAlias(name);
            u.setPw(pw);
            u.setAdmin(0); // 0 by default; 0 = regular user, 1 = admin, 2 = superadmin. (admins can only be added manually through MariaDB)
            uBean.insertToDb(u);
            
            json = new JSONObject();
            putJson(json, "status", "loggedIn");
            putJson(json, "id", uBean.findByX("Alias", name).getId());
            putJson(json, "email", email);
            putJson(json, "alias", name);
            putJson(json, "admin", 0);        
                             
            // TODO: on the client, since you are now registered and logged in, alter the login/register page to contain only 'logout' button
        }     
        return json;   
    } // end signUp()
    
    // fetch form data from the login form and try to log in with an existing user
    @POST
    @Path("LogIn")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject logIn(@FormParam("loginUsername") String name, @FormParam("loginPassword") String pw) {
    
        // TODO: call method that validates form arguments
        
        JSONObject json;
        User u = uBean.findByX("Alias", name); // check user existence by name
        
        if (notNull(u) && u.getPw().equals(pw)) {
            
            // TODO: since you are now logged in, alter the login/register page to contain only 'logout' button
            
            json = new JSONObject();
            putJson(json, "status", "loggedIn");
            putJson(json, "id", u.getId());
            putJson(json, "email", u.getEmail());
            putJson(json, "alias", name);
            putJson(json, "admin", u.getAdmin());
        } 
        else if (notNull(u) && !(u.getPw().equals(pw))) {
        
            json = setResponseStatus("wrongPw"); 
        } 
        else {    
             json = setResponseStatus("wrongUsername");       
        }
        return json;
    } // end logIn()  
} // end class
