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
    private String id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Bus bus;
    private Time departureTime;
    private Time arrivalApproximateTime;
    private double cost;
}
