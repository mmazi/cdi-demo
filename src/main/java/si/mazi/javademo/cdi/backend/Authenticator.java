package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class Authenticator implements Serializable {

    @Inject private AuthenticatorEJB authEjb;
    @Inject private Credentials creds;
    @Inject private @LoggedIn Event<LoginEvent> loginEvent;
    @Inject private @LoggedOut Event<LoginEvent> logoutEvent;

    @Produces @Dependent @Named("loggedInUser")
    private @LoggedIn User user;

    public void login() {
        user = authEjb.logIn(creds.getUsername(), creds.getPassword());
        loginEvent.fire(new LoginEvent(creds.getUsername(), user));
    }

    public void logout() {
        final LoginEvent event = new LoginEvent(user.getUsername(), user);
        user = null;
        logoutEvent.fire(event);
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public static class LoginEvent {
        public final String username;
        public final User user;

        private LoginEvent(String username, User user) {
            this.username = username;
            this.user = user;
        }
    }
}
