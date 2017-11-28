
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<User> getAll() {

        return em.createNamedQuery("User.findAll").getResultList();
    }
    
   public User findById(int id) {
   
       return (User)em.createNamedQuery("User.findById").setParameter("id", id).getResultList().get(0);
   }
    
    public User insert(User u) {
       
        em.persist(u); // insert user object into database
        return u;
    }
    
    public void update(User u){
        
        em.merge(u); // replace existing user entry in the db with the updated one (?)
    }
    
    
    
}
