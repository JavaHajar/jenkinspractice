package testDao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import dao.UserDao;
import dao.UserDaoImp;
import entities.Role;
import entities.User;

public class UserDaoImpTest {

	@Test
	public final void testFindAll() {
		 UserDao userDao = new UserDaoImp();
			Assertions.assertEquals(11,userDao.findAll().size());
	}

	@Test
	public final void testFindById() {
		UserDao userDao = new UserDaoImp();
		User admin = new User();
        admin = userDao.findById(2);
        Assertions.assertEquals("test", admin.getNom_user());
	}

	@Test
	public final void testSave() {
		UserDao userDao = new UserDaoImp();
		Role role = new Role("Admin");
		User user = new User( "najmi", "rachid", "rachid@gmail.com", "aimadrachid");
		user.setRole(role);
		userDao.save(user);
		
		assertEquals("najmi", user.getNom_user());
	}

	@Test
	public final void testUpdate() {
    UserDao userDao = new UserDaoImp();
	Role role = new Role("Admin");
	User user = new User( 10, "kolo", "rachid", "rachid@gmail.com", "aimadrachid");
	user.setRole(role);
	userDao.update(user);	
	assertEquals("kolo", user.getNom_user());
	}

	@Test
	public final void testDelete() {
		UserDao userDao = new UserDaoImp();
		userDao.delete(16);
	    Assert.assertNull(userDao.findById(16));
	}

}
