package model;

import enumuration.BusType;
import lombok.Data;

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
}
