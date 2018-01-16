package org.ogin.dao;

import org.ogin.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

/**
 * @author ogin
 */
@Repository("userDao")
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

    @Override
    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            initializeCollection(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public User findBySSO(String sso) {
        try {
            User user = (User) getEntityManager()
                    .createQuery("SELECT user FROM User user where user.ssoId LIKE :ssoId")
                    .setParameter("ssoId", sso)
                    .getSingleResult();
            if (user != null) {
                initializeCollection(user.getUserProfiles());
            }
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    protected void initializeCollection(Collection<?> collection) {
        if(collection == null)
            return;
        collection.iterator().hasNext();
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteBySSO(String sso) {
        User user = (User) getEntityManager()
                .createQuery("SELECT user FROM User user where user.ssoId LIKE :ssoId")
                .setParameter("ssoId", sso)
                .getSingleResult();
        delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT user FROM User user ORDER BY user.firstName ASC")
                .getResultList();
        return users;
    }
}
