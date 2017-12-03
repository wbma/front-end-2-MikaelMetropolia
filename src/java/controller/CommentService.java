/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
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
import model.Comment;
import model.Comp;
import org.json.JSONObject;
import static utils.Utils.putJson;
import static utils.Utils.setResponseStatus;

/**
 * REST Web Service
 *
 * @author Ville L
 */
@Path("CommentService")
public class CommentService {
    
    @EJB
    private CommentBean commBean;
    
    @EJB
    private CompBean compBean;

    public CommentService() {
    }

    // create new comment (based on form data)
    @POST
    @Path("AddComment")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject addComment(
            @FormParam("content") String content, 
            @FormParam("compid") int compid
            ) {
    
        // TODO: call some method to validate the data (check for null fields!)...
        // TODO: spam check...
        
        int userId = 99; // PLACEHOLDER (needs to come from the request header)
        Date addTime = new Date(System.currentTimeMillis()); // server time when it should be client... meh, who cares.    
        
        Comment c = new Comment();
        c.setUserid(userId);
        c.setContent(content);
        c.setAddtime(addTime);
        c.setCompid(compid);
        commBean.insertToDb(c);
        
        // increase number of comments on the relevant composition by 1. 
        // NOTE: there is likely a way to automate this operation somehow...
        Comp alteredComp = compBean.findByIntX("Id", compid); // this should always succeed (because of compid's origin)
        alteredComp.setComms(alteredComp.getComms()+1);
        compBean.updateDbEntry(alteredComp);
        
        JSONObject j = new JSONObject();
        putJson(j, "status", "addedComment");
        putJson(j, "content", content); // not strictly necessary, I guess... meh, let's send it anyway      
        putJson(j, "compId", compid);
        return j;
    } // end addComment()
    
    @POST
    @Path("EditComment")
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject editComment(@QueryParam("id") int commId, @FormParam("content") String newContent) { // the id comes from clicking on the comment to edit
        
        // TODO: check that you have the right to edit it (admin or adder)
        // TODO: there could be a time check as well... it could be hard to implement though
        
        Comment c = commBean.findByIntX("Id", commId);
     
        c.setContent(newContent);
        commBean.updateDbEntry(c);
        
        return setResponseStatus("editedComment"); 
    } // end editComp()
    
    @POST
    @Path("RemoveComment")
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject removeComment(@QueryParam("id") int id) { // the id comes from clicking on the comment to delete
        
        // TODO: check that you have the right to remove it (admin or adder)
        
        Comment c = commBean.findByIntX("Id", id);
        commBean.deleteFromDb(c);
        
        Comp alteredComp = compBean.findByIntX("Id", c.getCompid()); // the composition that the the comment was attached to
        
        int newComms = (alteredComp.getComms() > 0) ? alteredComp.getComms()-1 : 0; // if there's at least one comment, decrease the number of comments by one
        
        alteredComp.setComms(newComms);
        compBean.updateDbEntry(alteredComp);
        
        return setResponseStatus("removedComment"); 
    } // end removeComp()
    
    @POST
    @Path("GetAllCommentsOnComp")
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject getAllCommsByCompId(@QueryParam("id") int compId) { // the id comes from clicking on the composition
        
        
        List<Comment> cList = (List<Comment>)commBean.findByIntX("CompId", compId);
      
        JSONObject j = new JSONObject();
        putJson(j, "status", "gotCommentsByCompId");
        putJson(j, "commentList", cList);
        return j;
    } // end getAllCommsByCompId()
    
    @POST
    @Path("GetCommentByCompId")
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject getCommByCompId(@QueryParam("id") int compId) { // the id comes from clicking on the composition
        
        
        List<Comment> cList = (List<Comment>)commBean.findByIntX("CompId", compId);
      
        JSONObject j = new JSONObject();
        putJson(j, "status", "gotCommentsByCompId");
        putJson(j, "commentList", cList);
        return j;
    } // end getCommsByCompId()
} // end class()
