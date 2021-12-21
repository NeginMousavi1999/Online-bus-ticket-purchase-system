package view;

import dto.TicketDto;
import model.ticket.Ticket;
import model.ticket.TicketViewRequest;
import service.BusService;
import service.CompanyService;
import service.ManagerService;
import service.TicketService;
import util.CreateScanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class UserView {
    private final ManagerService managerService = new ManagerService();
    private final TicketService ticketService = new TicketService();
    private final CompanyService companyService = new CompanyService();
    private final BusService busService = new BusService();
    private final Scanner scanner = CreateScanner.getInstance();

    public void loginUser() {
        showTicketsDto();
    }

    private void showTicketsDto() {
        TicketViewRequest request = getTicketViewRequest();

        System.out.print("count of results: ");
        String countOfResults = scanner.nextLine();
        int start = 0;
        int pageSize = Integer.parseInt(countOfResults);

        loop:
        while (true) {
            int result = showTickets(start, pageSize, request);
            forContinuing:
            while (true) {
                System.out.print("show details? next? previous? exit? (s/n/p/e): ");
                String answer = scanner.nextLine();
                switch (answer) {
                    case "s":
                        showTicketDetail();
                        //TODO choose ticket or not
                        break;
                    case "n":
                        if (result < pageSize) {
                            System.out.println("no next page!");
                            continue forContinuing;
                        }
                        start += pageSize;
                        break;
                    case "p":
                        if (start == 0) {
                            System.out.println("no previous page!");
                            continue forContinuing;
                        }
                        start -= pageSize;
                        break;
                    case "e":
                        System.out.println("bye bye");
                        break loop;
                    default:
                        Main.printInvalidInput();
                        break loop;
                }
            }
        }
    }

    private TicketViewRequest getTicketViewRequest() {
        TicketViewRequest request = new TicketViewRequest();
        System.out.println("to know about our trips, please answer the questions:");
        System.out.print("origin: ");
        String origin = scanner.nextLine();
        request.setOrigin(origin);
        System.out.print("destination: ");
        String destination = scanner.nextLine();
        request.setDestination(destination);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("date(yyyy-MM-dd) (optional): ");
        String date = scanner.nextLine();
        try {
            Date requestDate = simpleDateFormat.parse(date);
            request.setDate(requestDate);
        } catch (ParseException e) {
            System.out.println("your date will be optional");
            request.setDate(null);
        }
        return request;
    }

    private void showTicketDetail() {
        System.out.print("enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Ticket ticketById = ticketService.getTicketById(id);
        if (ticketById == null)
            throw new RuntimeException("no ticket with this id!");
        System.out.println(ticketById);
    }

    private int showTickets(int currentPage, int pageSize, TicketViewRequest request) {
        List<TicketDto> ticketsDto = ticketService.showTicketsByPaging(currentPage, pageSize, request);
        System.out.printf("***** page number %d *****", (currentPage + 1));
        System.out.println();
        ticketsDto.forEach(System.out::println);
        return ticketsDto.size();
    }

    private int getCountOfSeatsPerBusClass() {
        return 0;
    }
}
