package dto;

import lombok.Data;
import model.Bus;
import model.Company;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class TicketDto {
    private int id;
    private Company company;
    private Bus bus;
    private Date departureTime;
    private Date departureDate;
    private double cost;
    private int count;
}
