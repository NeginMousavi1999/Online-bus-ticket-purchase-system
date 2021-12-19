package view;

import service.BusService;
import service.CompanyService;
import service.ManagerService;
import service.TicketService;

import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class UserView {
    private final ManagerService managerService = new ManagerService();
    private final TicketService ticketService = new TicketService();
    private final CompanyService companyService = new CompanyService();
    private final BusService busService = new BusService();
    private final Scanner scanner = new Scanner(System.in);

    public void loginUser() {
        System.out.println("to know about our trips, please answer the questions:");
        System.out.print("origin: ");
        String origin = scanner.nextLine();
        System.out.print("destination: ");
        String destination = scanner.nextLine();
        System.out.print("date: ");
        String date = scanner.nextLine();
        System.out.print("count of results: ");
        String countOfResults = scanner.nextLine();

    }

    private int getCountOfSeatsPerBusClass() {
        return 0;
    }
}
