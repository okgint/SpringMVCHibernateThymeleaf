package org.ogin.dao;

import org.ogin.entity.UserProfile;

import java.util.List;

/**
 * @author ogin
 */
public interface UserProfileDAO {
    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
