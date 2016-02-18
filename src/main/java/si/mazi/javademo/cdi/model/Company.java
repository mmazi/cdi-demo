package si.mazi.javademo.cdi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Company {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "employer")
    private List<User> employees;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return String.format("Company{id=%d, name='%s'}", id, name);
    }
}
