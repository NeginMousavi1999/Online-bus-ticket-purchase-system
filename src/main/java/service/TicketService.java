package service;

import dao.TicketDao;
import model.Ticket;

/**
 * @author Negin Mousavi
 */
public class TicketService {
    TicketDao ticketDao = new TicketDao();

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }
}
