import enumuration.BusType;
import model.Bus;
import model.Company;
import model.Ticket;
import model.member.Manager;
import service.ManagerService;
import service.TicketService;

import java.sql.Time;

/**
 * @author Negin Mousavi
 */
public class Main {
    static ManagerService managerService = new ManagerService();
    static TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        managerService.init();

        Company company = new Company();
        company.setName("Homa");

        Bus bus = new Bus();
        bus.setType(BusType.CLASS_A);
        bus.setTotalSeats(20);
        bus.setSeatsRemaining(20);

        Ticket ticket = new Ticket();
        ticket.setOrigin("Tehran");
        ticket.setDestination("NewYork");
        ticket.setBus(bus);
        ticket.setCompany(company);
        ticket.setCost(10000);
        ticket.setDepartureTime(new Time(12));
        ticket.setArrivalApproximateTime(new Time(15));

        company.getTickets().add(ticket);
        bus.getTickets().add(ticket);

        ticketService.save(ticket);
    }
}
