package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.User;

public class AdminReposImp implements AdminRepos {

	@Override
	public boolean validate(String email, String password) {
		try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		User user  = (User) em.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email).getSingleResult();
            if (user != null && user.getPassword().equals(password)) {
                return true;
            };
            }
           
        catch (Exception e) {
            
            e.printStackTrace();
        }
        return false;
    }
	

}
