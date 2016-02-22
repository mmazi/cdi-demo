package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthenticatorEJB {

    @PersistenceContext private EntityManager em;

    public User logIn(String username, String password) throws NoResultException {
        try {
            return em.createQuery("select u from User u where u.username = :uname and u.password = :pwd", User.class)
                    .setParameter("uname", username)
                    .setParameter("pwd", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
