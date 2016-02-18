package si.mazi.javademo.cdi.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.javademo.cdi.model.Company;
import si.mazi.javademo.cdi.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataInit {
    private static final Logger log = LoggerFactory.getLogger(DataInit.class);

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        log.debug("DevDbInit.init");

        deleteAll();

        final Company poligon = createCompany("Poligon");
        final Company openBlend = createCompany("OpenBlend");

        createUser("gasper", "Gašper", poligon);
        createUser("miha", "Miha", poligon);
        createUser("boltezar", "Boltežar", openBlend);
    }

    public void deleteAll() {
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Company").executeUpdate();
    }

    private Company createCompany(String name) {
        final Company company = new Company();
        company.setName(name);
        em.persist(company);
        return company;
    }

    public User createUser(String username, String name, Company company) {
        User user = new User(username);
        user.setPassword("pwd");
        user.setName(name);
        user.setEmployer(company);

        em.persist(user);
        return user;
    }


}
