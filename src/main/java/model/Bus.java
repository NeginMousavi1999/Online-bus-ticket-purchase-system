package model;

import enumuration.BusType;
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
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalSeats;
    private int seatsRemaining;
    @Enumerated(EnumType.STRING)
    private BusType type;
    @OneToMany(mappedBy = "bus")
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", totalSeats=" + totalSeats +
                ", seatsRemaining=" + seatsRemaining +
                ", type=" + type +
                '}';
    }

    public void reserve(int count) {
        if (count > seatsRemaining)
            throw new RuntimeException("no seats!");
        seatsRemaining -= count;
    }
}
