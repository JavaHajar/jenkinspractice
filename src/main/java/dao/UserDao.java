package dao;

import java.util.List;

import entities.User;

public interface UserDao {
	List<User> findAll();
    User findById(int id);
    void save(User user);
    void update (User user);
    void delete(int id);

}
