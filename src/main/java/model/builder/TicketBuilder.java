package model.builder;

import model.Ticket;

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
}
