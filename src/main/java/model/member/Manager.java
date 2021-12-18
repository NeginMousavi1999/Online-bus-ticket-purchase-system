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
    protected String username;
    protected String password;
}
