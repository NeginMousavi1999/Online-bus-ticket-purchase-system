package model.ticket;

import lombok.Data;
import model.Bus;
import model.Company;

import javax.persistence.*;
import java.util.Date;

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
    @ManyToOne(cascade = CascadeType.ALL) //TODO: remove cascadeAll
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL) //TODO: remove cascadeAll
    private Bus bus;
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalApproximateTime;
    private double cost;
    private int count;

    public void buy(int count) {
        if (count > this.count)
            throw new RuntimeException("*** not enough tickets! ***");
        this.count -= count;
    }
}
