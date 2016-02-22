package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class TitleProvider {

    @Inject @LoggedIn Instance<User> userInstance;

    @Produces @Dependent @Named("pageTitle")
    public String getPageTitle() {
        final User loggedInUser = userInstance.get();
        if (loggedInUser != null) {
            return "CDI / " + loggedInUser.getName();
        }
        return "CafeBabe CDI demo";
    }
}
