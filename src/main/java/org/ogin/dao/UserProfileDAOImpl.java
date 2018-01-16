package org.ogin.dao;

import org.ogin.entity.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author ogin
 */
@Repository("userProfileDao")
public class UserProfileDAOImpl extends AbstractDAO<Integer, UserProfile> implements UserProfileDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = getEntityManager()
                .createQuery("SELECT profile from UserProfile profile ORDER BY profile.type ASC")
                .getResultList();
        return userProfiles;
    }

    @Override
    public UserProfile findByType(String type) {
        try {
            UserProfile userProfile = (UserProfile) getEntityManager()
                    .createQuery("SELECT profile FROM UserProfile profile where profile.type LIKE :type")
                    .setParameter("type", type)
                    .getSingleResult();

            return userProfile;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserProfile findById(int id) {
        return getByKey(id);
    }
}
