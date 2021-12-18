package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String origin;
    private String destination;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL)
    private Bus bus;
    private Time departureTime;
    private Time arrivalApproximateTime;
    private double cost;
}
