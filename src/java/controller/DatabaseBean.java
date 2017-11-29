
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.User;

/**
 *
 * @author Ville L
 */
@Stateless
public class DatabaseBean {

    @PersistenceContext
    private EntityManager em; // mediates between RestfulDbService and DatabaseBean (what about 'User'? doesn't it use that as well?)

    public List<User> getAllUsers() {

        return em.createNamedQuery("User.findAll").getResultList();
    }
    
   public User findById(int id) {
      
       try { 
            User u = (User)em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
            return u;
        } catch (NoResultException e) {
            return null;
        }
   }
    
    public User insertToDb(User u) {
       
        em.persist(u); // insert user object into database (auto-picks the correct table based on object type ??)
        return u;
    }
    
    public void updateDbEntry(User u){
        
        em.merge(u); // replace existing user entry in the db with the updated one (?)
    }
    
    // delete user from the database (hopefully...)
    public void deleteFromDb(User u) {
        
        em.createNamedQuery("User.deleteUser").setParameter("id", u.getId()).getResultList().get(0);
    }
    
    // checks whether a user with a certain stat (name, email, etc) exists in the User database and returns it
    // NOTE: use a big initial letter in 'stat' for this to work!
    public User findByX(String stat, String arg) {
        
        try {
            User u = (User)em.createNamedQuery("User.findBy"+stat).setParameter(stat.toLowerCase(), arg).getSingleResult();         
            return u;
        } catch (NoResultException e) {
            return null;
        }    
    } // end findByX()
      
} // end class
