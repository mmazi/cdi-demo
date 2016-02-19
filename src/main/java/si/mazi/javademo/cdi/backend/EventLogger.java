package si.mazi.javademo.cdi.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import java.io.Serializable;

@RequestScoped
public class EventLogger implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(EventLogger.class);

    public void logLogin(@Observes @LoggedIn Authenticator.LoginEvent event) {
        if (event.user == null) {
            log.warn("Failed login: {}", event.username);
        } else {
            log.info("User logged in: {}", event.user);
        }
    }

    public void logLogout(@Observes @LoggedOut Authenticator.LoginEvent event) {
        log.info("User logged out: {}", event.user);
    }
}
