package model.builder;

import model.Bus;
import model.Company;
import model.Ticket;

import java.sql.Time;

/**
 * @author Negin Mousavi
 */
public class TicketBuilder {
    private final Ticket ticket = new Ticket();

    public static TicketBuilder getBuilder() {
        return new TicketBuilder();
    }

    public Ticket build() {
        return ticket;
    }

    public TicketBuilder withOrigin(String origin) {
        ticket.setOrigin(origin);
        return this;
    }

    public TicketBuilder withDestination(String destination) {
        ticket.setDestination(destination);
        return this;
    }

    public TicketBuilder withArrivalApproximateTime(Time time) {
        ticket.setArrivalApproximateTime(time);
        return this;
    }

    public TicketBuilder withDepartureTime(Time time) {
        ticket.setDepartureTime(time);
        return this;
    }

    public TicketBuilder withCost(double cost) {
        ticket.setCost(cost);
        return this;
    }

    public TicketBuilder withCompany(Company company) {
        ticket.setCompany(company);
        return this;
    }

    public TicketBuilder withBus(Bus bus) {
        ticket.setBus(bus);
        return this;
    }
}
