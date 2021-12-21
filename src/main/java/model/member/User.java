package model.member;

import enumuration.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.ticket.Ticket;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@ToString(callSuper = true)
@Setter
@Getter
@Entity
public class User extends Person {
    @Column(name = "first_name")
    private String givenName;
    @Column(name = "last_name")
    private String surName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    @OneToOne
    private Ticket ticket;
}
