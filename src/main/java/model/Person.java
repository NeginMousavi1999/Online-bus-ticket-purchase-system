package model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(unique = true)
    protected String username;
    protected String password;
}
