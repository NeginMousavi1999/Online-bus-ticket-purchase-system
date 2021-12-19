import model.Bus;
import model.Company;
import model.Ticket;
import model.builder.TicketBuilder;
import model.member.Manager;
import service.ManagerService;
import service.TicketService;

import java.sql.Time;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class Main {
    static ManagerService managerService = new ManagerService();
    static TicketService ticketService = new TicketService();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        managerService.init();

        System.out.print("Hi ^_^ welcome to our online bus ticket...\nlogin as 1.admin or 2.user?(1/2): ");
        String loginAnswer = scanner.nextLine();
        switch (loginAnswer) {
            case "1":
                loginAdmin();
                break;
            case "2":
                loginUser();
                break;
            default:
                printInvalidInput();
        }
    }

    private static void printInvalidInput() {
        System.out.println("invalid input!");
    }

    private static void loginUser() {
        System.out.println("to know about our trips, please answer the questions:");
        System.out.print("orion: ");
        String orion = scanner.nextLine();
        System.out.print("destination: ");
        String destination = scanner.nextLine();
        System.out.print("date: ");
        String date = scanner.nextLine();
        System.out.print("count of results: ");
        String countOfResults = scanner.nextLine();
    }

    private static void loginAdmin() {
        Manager manager = managerService.getManager();
        System.out.print("enter username: ");
        String username = scanner.nextLine();
        System.out.print("enter password: ");
        String password = scanner.nextLine();
        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            System.out.println("*** Admin Menu ***");
            // TODO : belows :)
            //تعداد بلیط های فروخته شده
            // تعداد صندلی های باقیمانده بر اساس نوع اتوبوس
            // به ترتیب تاریخ حرکت
            addNewTicket();
        } else
            System.out.println("incorrect username or password!");
    }

    private static void addNewTicket() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        Ticket ticket = ticketBuilder
                .withBus("")
                .withDestination("")
                .withCost(0)
                .withDepartureTime(new Time(0))
                .withArrivalApproximateTime(new Time(0))
                .withCompany(new Company())
                .withBus(new Bus())
                .build();
        ticketService.save(ticket);
    }

    private int getCountOfSeatsPerBusClass(){
        return 0;
    }
}
