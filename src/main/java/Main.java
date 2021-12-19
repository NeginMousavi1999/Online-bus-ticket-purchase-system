import enumuration.BusType;
import enumuration.CityValue;
import model.Bus;
import model.Company;
import model.Ticket;
import model.builder.TicketBuilder;
import model.member.Manager;
import service.BusService;
import service.CompanyService;
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
    static CompanyService companyService = new CompanyService();
    static BusService busService = new BusService();
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
            loop:
            while (true) {
                System.out.print("1.Add new Bus/Company/Ticket\n2.show information\n3.exit\nyour answer: ");
                String answer = scanner.nextLine();
                switch (answer) {
                    case "1":
                        addNewInformation();
                        break;
                    case "2":
                        showInformation();
                        break;
                    case "3":
                        System.out.println("...end of admin role...");
                        break loop;
                    default:
                        printInvalidInput();
                }
            }
            // TODO : belows :)
            //تعداد بلیط های فروخته شده
            // تعداد صندلی های باقیمانده بر اساس نوع اتوبوس
            // به ترتیب تاریخ حرکت
        } else
            System.out.println("incorrect username or password!");
    }

    private static void showInformation() {//TODO

    }

    private static void addNewInformation() {
        System.out.print("what do you wanna add?\n1.Company\n2.Bus\n3.Ticket\nyour answer: ");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                addNewCompany();
                break;
            case "2":
                addNewBus();
                break;
            case "3":
                addNewTicket();
                break;
            default:
                printInvalidInput();
        }
    }

    private static void addNewBus() {
        Bus bus = new Bus();
        System.out.print("enter total seats: ");
        int seats = Integer.parseInt(scanner.nextLine());
        System.out.print("enter bus type: 1.vip 2.regular\ntype: ");
        String type = scanner.nextLine();
        switch (type) {
            case "1":
                bus.setType(BusType.VIP);
                break;
            case "2":
                bus.setType(BusType.REGULAR);
                break;
            default:
                printInvalidInput();
                System.out.println("...set default type...");
                bus.setType(BusType.REGULAR);
        }
        bus.setTotalSeats(seats);
        bus.setSeatsRemaining(seats);
        busService.save(bus);
    }

    private static void addNewCompany() {
        System.out.print("company name: ");
        String name = scanner.nextLine();
        Company company = new Company();
        company.setName(name);
        companyService.save(company);
    }

    private static void addNewTicket() {
        Bus bus;
        Company company;

        System.out.print("enter bus type: 1.vip 2.regular\ntype: ");
        String type = scanner.nextLine();
        try {
            switch (type) {
                case "1":
                    bus = busService.getRandomByType(BusType.VIP);
                    break;
                case "2":
                    bus = busService.getRandomByType(BusType.REGULAR);
                    break;
                default:
                    printInvalidInput();
                    System.out.println("...default type...");
                    bus = busService.getRandomByType(BusType.REGULAR);
            }

            System.out.print("enter company name: ");
            String companyName = scanner.nextLine();
            company = companyService.getByName(companyName);

        } catch (RuntimeException re) {
            System.out.println(re.getLocalizedMessage());
            return;
        }

        System.out.print("enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("enter destination: ");
        String destination = scanner.nextLine();

        double cost = calculateTicketCostByBusTypeOriginDestination(bus, origin, destination);
        if (cost == -1)
            return;

        TicketBuilder ticketBuilder = new TicketBuilder();
        Ticket ticket = ticketBuilder
                .withOrigin(origin)
                .withDestination(destination)
                .withCost(cost)
                .withDepartureTime(new Time(0))//TODO
                .withArrivalApproximateTime(new Time(0))
                .withCompany(company)
                .withBus(bus)
                .build();
        ticketService.save(ticket);
    }

    public static double calculateTicketCostByBusTypeOriginDestination(Bus bus, String origin, String destination) {
        double originValue;
        double destinationValue;

        try {
            originValue = CityValue.getAbbrByValue(CityValue.valueOf(origin.toUpperCase()));
            destinationValue = CityValue.getAbbrByValue(CityValue.valueOf(destination.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("*** we have n't trip for this city! ***");
            return -1;
        }

        return (originValue + destinationValue) * BusType.getAbbrByValue(bus.getType()) * 10000;
    }

    private int getCountOfSeatsPerBusClass() {
        return 0;
    }
}
