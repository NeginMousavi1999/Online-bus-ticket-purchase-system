package model.ticket;

import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class TicketViewRequest {
    private String origin;
    private String destination;
    private Date date;
}
