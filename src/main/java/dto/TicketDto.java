package dto;

import lombok.Data;
import model.Bus;
import model.Company;

import java.sql.Time;

/**
 * @author Negin Mousavi
 */
@Data
public class TicketDto {
    private int id;
    private Company company;
    private Bus bus;
    private Time departureTime;
    private double cost;
}
