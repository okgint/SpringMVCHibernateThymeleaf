package org.ogin.services;

import org.ogin.entity.UserProfile;

import java.util.List;

/**
 * @author ogin
 */
public interface UserProfileService {
    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
