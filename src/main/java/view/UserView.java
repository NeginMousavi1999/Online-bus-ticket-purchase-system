package view;

import dto.TicketDto;
import enumuration.BusType;
import enumuration.Gender;
import model.builder.UserBuilder;
import model.member.User;
import model.ticket.Ticket;
import model.ticket.TicketViewRequest;
import service.TicketService;
import service.UserService;
import util.CreateScanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
public class UserView {
    private final TicketService ticketService = new TicketService();
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
                        continue loop;
                    case "p":
                        if (start == 0) {
                            System.out.println("no previous page!");
                            continue forContinuing;
                        }
                        start -= pageSize;
                        continue loop;
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
        ticketsDto = filter(ticketsDto);
        System.out.printf("***** page number %d *****", (currentPage + 1));
        System.out.println();
        ticketsDto.forEach(System.out::println);
        return ticketsDto.size();
    }

    private List<TicketDto> filter(List<TicketDto> tickets) {
        System.out.print("more filter?(y/n): ");
        String moreFilter = scanner.nextLine();
        if (moreFilter.equals("n"))
            return tickets;
        List<TicketDto> newTickets;
        System.out.print("company name: ");
        String companyName = scanner.nextLine();
        if (companyName.length() != 0)
            newTickets = tickets.stream().filter(ticket -> ticket.getCompany().getName().equals(companyName)).collect(Collectors.toList());
        else {
            System.out.println("optional company name");
            newTickets = new ArrayList<>(tickets);
        }
        System.out.print("bus type: ");
        try {
            BusType busType = BusType.valueOf(scanner.nextLine().toUpperCase());
            newTickets = newTickets.stream().filter(ticket -> ticket.getBus().getType().equals(busType)).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("optional bus type");
        }

        double startCost;
        double endCost;
        System.out.print("cost range(split with ,): ");
        String costRange = scanner.nextLine();
        if (costRange.length() != 0) {
            String[] costRanges = costRange.split(",");
            startCost = Double.parseDouble(costRanges[0]);
            endCost = Double.parseDouble(costRanges[1]);
            newTickets = newTickets.stream().filter(ticket -> ticket.getCost() >= startCost)
                    .filter(ticket -> ticket.getCost() <= endCost).collect(Collectors.toList());
        }
        System.out.print("date range(split with ,): ");
        String dateRange = scanner.nextLine();
        Date startDate;
        Date endDate;
        if (dateRange.length() != 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String[] dateRanges = dateRange.split(",");
                startDate = simpleDateFormat.parse(dateRanges[0]);
                endDate = simpleDateFormat.parse(dateRanges[1]);
                newTickets = newTickets.stream().filter(ticket -> ticket.getDepartureDate().compareTo(startDate) >= 0)
                        .filter(ticket -> ticket.getDepartureDate().compareTo(endDate) <= 0).collect(Collectors.toList());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return newTickets;
    }
}
