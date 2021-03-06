package model;

import lombok.Data;
import model.ticket.Ticket;

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
    private int id;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
