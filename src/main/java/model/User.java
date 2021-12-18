package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author Negin Mousavi
 */
@ToString(callSuper = true)
@Setter
@Getter
@Entity
public class User extends Person {
}
