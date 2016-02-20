package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class TitleProvider {

    @Inject @LoggedIn Instance<User> loggedInUserInstance;
    @Inject @Edited Instance<User> editedUserInstance;

    private String requestTitle;

    @Produces @Dependent @Named("pageTitle")
    public String getPageTitle() {
        if (requestTitle != null) {
            return requestTitle;
        }
        final User editedUser = editedUserInstance.get();
        if (editedUser != null) {
            return "CDI / Editing " + editedUser.getName();
        }
        final User loggedInUser = loggedInUserInstance.get();
        if (loggedInUser != null) {
            return "CDI / " + loggedInUser.getName();
        }
        return "CafeBabe CDI demo";
    }

    public void afterLogin(@Observes @LoggedIn Authenticator.LoginEvent event) {
        requestTitle = event.user == null ? "Ooops.." : String.format("Welcome, %s!", event.user.getName());
    }
}
