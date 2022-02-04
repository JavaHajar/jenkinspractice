package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.User;

public class UserDaoImp implements UserDao {

	@Override
	 @SuppressWarnings("unchecked")
	public List<User> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select a from User a", User.class);
        return query.getResultList();
		

	}

	@Override
	public User findById(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, id);
	        return user;

	}

	@Override
	public void save(User user) {
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user); 
			em.getTransaction().commit();
		
	}

	@Override
	public void update(User user) {
	
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user); 
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
        User user = null;
        try{
        	 user = em.find(User.class, id);
             em.getTransaction().begin();
             em.remove(user);
             em.getTransaction().commit();
          
        } catch(Exception e) {
            e.printStackTrace();
        }
   
	}

}
