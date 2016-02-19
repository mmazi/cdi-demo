package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.Company;
import si.mazi.javademo.cdi.model.User;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ListItemDataProducer implements Serializable {
    @PersistenceContext private EntityManager em;

    @Produces @RequestScoped @Named
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Produces @RequestScoped @Named
    public List<Company> getAllCompanies() {
        return em.createQuery("select u from Company u", Company.class).getResultList();
    }
}
