/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.User;

/**
 * REST Web Service. Exposed to the outside world (point of communication). Tied to form fields via @FormParam
 *
 * @author Ville L
 */
@Path("RestfulDbService")
public class RestfulDbService {

    @EJB
    private DatabaseBean dbBean;

    /**
     * Creates a new instance of RestfulDbService
     */
    public RestfulDbService() {
    }

    /**
     * Retrieves representation of an instance of controller.RestfulDbService
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    */
    /**
     * PUT method for updating or creating an instance of RestfulDbService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    /*
    // get all database entries
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getJson() {
        
        return dbBean.getAll();
    } */
    /*
    // insert form data to a new database entry
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    public User post(@FormParam("email") String email, @FormParam("alias") String alias, @FormParam("pw") String pw, @FormParam("admin") int admin) {
    
        User u = new User();
        u.setEmail(email);
        u.setAlias(alias);
        u.setPw(pw);
        u.setAdmin(admin);
        return dbControl.insert(u);
    } */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@QueryParam("id") int id) {
        
        User u = dbBean.findById(id);
        return u;
    }
    
}
