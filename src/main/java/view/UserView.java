package view;

import dto.TicketDto;
import enumuration.Gender;
import model.builder.UserBuilder;
import model.member.User;
import model.ticket.Ticket;
import model.ticket.TicketViewRequest;
import service.*;
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
    private final UserService userService = new UserService();
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
        bookTicket(ticketById);
    }

    private void bookTicket(Ticket ticket) {
        System.out.print("do you wanna book it?(y/n): ");
        String answer = scanner.nextLine();
        switch (answer) {
            case "y":
                getInformationForBookTicket(ticket);
                break;
            case "n":
                return;
            default:
                Main.printInvalidInput();
        }
    }

    private void getInformationForBookTicket(Ticket ticket) {
        System.out.print("enter the count: ");
        int count = Integer.parseInt(scanner.nextLine());
        try {
            ticket.buy(count);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        int index = 1;
        while (index <= count) {
            System.out.println("*** get information of user number " + index + " ***");
            System.out.print("enter national code: ");
            String nationalCode = scanner.nextLine();
            System.out.print("enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("enter first name: ");
            String givenName = scanner.nextLine();
            System.out.print("enter last name: ");
            String surName = scanner.nextLine();
            System.out.print("enter gender(male/female): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            User user = UserBuilder.getBuilder()
                    .withNationalCode(nationalCode)
                    .withPhoneNumber(phoneNumber)
                    .withGivenName(givenName)
                    .withSurName(surName)
                    .withGender(gender)
                    .withAge(age)
                    .withTicket(ticket)
                    .build();
            userService.save(user);
            System.out.println("...DONE...");
            index++;
        }
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
