package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class Authenticator implements Serializable {

    @Inject private AuthenticatorEJB authEjb;
    @Inject private Credentials creds;

    @Produces @Dependent @Named("loggedInUser")
    private @LoggedIn User user;

    public void login() {
        user = authEjb.logIn(creds.getUsername(), creds.getPassword());
    }

    public void logout() {
        user = null;
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
