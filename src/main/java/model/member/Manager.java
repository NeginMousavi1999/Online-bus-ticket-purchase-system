package model.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Negin Mousavi
 */
@ToString(callSuper = true)
@Setter
@Getter
@Entity
public class Manager extends Person {
    @Column(unique = true)
    private String username;
    private String password;


    private static Manager manager;

    public synchronized static Manager getInstance() {
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    protected Manager() {
    }
}
