package service;

import dao.TicketDao;
import dto.TicketDto;
import model.ticket.Ticket;
import model.ticket.TicketViewRequest;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
public class TicketService {
    TicketDao ticketDao = new TicketDao();
    private final ModelMapper modelMapper = new ModelMapper();

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public List<TicketDto> showTicketsByPaging(int currentPage, int pageSize, TicketViewRequest request) {
        return ticketDao.showTicketsByPaging(currentPage, pageSize, request).stream().map(this::createTicketDto).collect(Collectors.toList());
    }

    private TicketDto createTicketDto(Ticket ticket) {
        TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);
        ticketDto.setId(ticket.getId());
        ticketDto.setCost(ticket.getCost());
        ticketDto.setBus(ticket.getBus());
        ticketDto.setCompany(ticket.getCompany());
        ticketDto.setDepartureDate(ticket.getDepartureDate());
        ticketDto.setDepartureTime(ticket.getDepartureTime());
        return ticketDto;
    }

    public Ticket getTicketById(int id) {
        return ticketDao.getById(id);
    }

    public Date getDateById(int id) {
        return ticketDao.getDateById(id);
    }
}
