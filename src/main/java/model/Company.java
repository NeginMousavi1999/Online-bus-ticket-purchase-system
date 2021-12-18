package model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @OneToMany(mappedBy = "company")
    private List<Ticket> tickets = new ArrayList<>();
}
