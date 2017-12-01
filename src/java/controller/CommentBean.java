/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Comment;

/**
 *
 * @author Ville L
 */
@Stateless
public class CommentBean {

    @PersistenceContext
    private EntityManager em;
    
    public List<Comment> getAllComments() {

        return em.createNamedQuery("Comment.findAll").getResultList();
    }
    
    public Comment insertToDb(Comment c) {
       
        try { 
            em.persist(c);
        } 
        catch (Exception e) {
            c = null;
        }
        return c;
    }
    
    public void updateDbEntry(Comment c){
        
        em.merge(c); // replace existing user entry in the db with the updated one (?)
    }
    
    // delete user from the database (hopefully...)
    public void deleteFromDb(Comment c) {
        
        em.createNamedQuery("Comment.deleteComment").setParameter("id", c.getId()).getSingleResult();
    }    
    
   // A method to fetch all comments with a certain int stat (in practice, userId or compId)
   public List<Comment> findAllByIntX(String stat, int arg) {
      
        try {
            List<Comment> c = (List<Comment>)em.createNamedQuery("Comment.findBy"+stat).setParameter(stat.toLowerCase(), arg).getResultList();
            return c;
        } catch (NoResultException e) {
            return null;
        }  
   } // end findAllByIntX()
   
    // not sure if this is the best place for the method, but it needs to be somewhere and it has a query operation with entityManager, so I put it here
    public int numberOfCommsOnComp(int compId) {
    
       return (int)em.createNamedQuery("Comment.countCommsOnComp").setParameter("compid", compId).getSingleResult();
    }
       
    // TODO: method to calculate number of likes... Not sure how to do this, as the 'Likes' table doesn't exist as its own entity
   
   
   
} // end class
