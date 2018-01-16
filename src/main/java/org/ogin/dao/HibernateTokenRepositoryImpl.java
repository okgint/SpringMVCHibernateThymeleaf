package org.ogin.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.ogin.entity.PersistenLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ogin
 */
@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDAO<String, PersistenLogin>
		implements PersistentTokenRepository {

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistenLogin persistentLogin = new PersistenLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistenLogin persistentLogin = (PersistenLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            System.out.println("WHAT__________________");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistenLogin persistentLogin = (PersistenLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }

    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistenLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }
}
