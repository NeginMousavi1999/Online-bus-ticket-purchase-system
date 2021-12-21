package model;

import model.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Negin Mousavi
 */
public class TicketBuyTest {
    Ticket ticket = new Ticket();
    Bus bus = new Bus();

    @BeforeEach
    void init() {
        bus.setTotalSeats(10);
        bus.setSeatsRemaining(8);
        ticket.setBus(bus);
    }

    @Test
    void givenCountOfTicketLessThanRemaining_WhenBuyCalls_ThenResponseReturn() {
        ticket.buy(5);
        assertEquals(ticket.getBus().getSeatsRemaining(), 3);
    }

    @Test
    void givenCountOfTicketMoreThanRemaining_WhenBuyCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> ticket.buy(10));
        assertEquals("*** not enough tickets! ***", exception.getMessage());
    }
}
