package si.mazi.javademo.cdi.backend;

import si.mazi.javademo.cdi.model.Employee;
import si.mazi.javademo.cdi.model.User;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ConversationScoped
@Stateful
@Named
public class UserEditor implements Serializable {

    @PersistenceContext private EntityManager em;
    @Inject private Conversation conversation;

    private User user;
    private boolean isNew;
    @NotNull private User.Type type;
    @NotNull private String username;

    /////// Conversation steps

    public String editNew() {
        this.user = null;
        this.isNew = true;
        this.type = User.Type.Employee;
        this.conversation.begin();
        return "user-new";
    }

    public String editUser(User u) {
        this.user = u;
        this.isNew = false;
        this.type = u.getType();
        this.username = u.getUsername();
        this.conversation.begin();
        return "user-edit";
    }

    public String createNew() {
        user = type == User.Type.Employee ? new Employee(username) : new User(username);
        return "user-edit";
    }

    public String saveName() {
        return user.getType() == User.Type.Employee ? "user-edit-employee" : "user-review";
    }

    public String save() {
        if (isNew) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        conversation.end();
        return "index";
    }

    public String cancel() {
        user = null;
        conversation.end();
        return "index";
    }

    /////// Getters / setters

    public Boolean getIsNew() {
        return isNew;
    }

    public User.Type getType() {
        return type;
    }

    public void setType(User.Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User.Type> getAllTypes() {
        return Arrays.asList(User.Type.values());
    }

    @Produces @Dependent @Edited
    public User getUser() {
        return user;
    }
}
