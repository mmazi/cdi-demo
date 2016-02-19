package si.mazi.javademo.cdi.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Employee extends User {

    @ManyToOne @NotNull
    private Company employer;

    protected Employee() {
        type = Type.Employee;
    }

    public Employee(String username) {
        super(username, Type.Employee);
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }
}
