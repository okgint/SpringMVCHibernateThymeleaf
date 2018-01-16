package org.ogin.dao;

import org.ogin.entity.User;

import java.util.List;

/**
 * @author ogin
 */
public interface UserDAO {
    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}
