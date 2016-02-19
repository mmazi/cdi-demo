package si.mazi.javademo.cdi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @NotNull @Size(min = 1, max = 32)
    private String username;

    @NotNull @Size(min = 3, max = 128)
    private String password;

    @NotNull @Size(min = 1, max = 256)
    private String name;

    @Transient
    protected Type type;

    protected User() {
        type = Type.User;
    }

    public User(String username) {
        this(username, Type.User);
    }

    public User(String username, Type type) {
        this.username = username;
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("User{%s}", username);
    }

    public enum Type { User, Employee }
}
