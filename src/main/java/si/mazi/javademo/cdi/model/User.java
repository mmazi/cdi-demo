package si.mazi.javademo.cdi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @NotNull @Size(min = 1, max = 32)
    private String username;

    @NotNull @Size(min = 3, max = 128)
    private String password;

    @NotNull @Size(min = 1, max = 256)
    private String name;

    @ManyToOne
    private Company employer;

    protected User() { }

    public User(String username) {
        this.username = username;
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

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return String.format("User{%s}", username);
    }
}
