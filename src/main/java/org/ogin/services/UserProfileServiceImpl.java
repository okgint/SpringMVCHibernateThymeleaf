package org.ogin.services;

import org.ogin.dao.UserProfileDAO;
import org.ogin.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ogin
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileDAO userProfileDAO;

    @Override
    public UserProfile findById(int id) {
        return userProfileDAO.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileDAO.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDAO.findAll();
    }
}
