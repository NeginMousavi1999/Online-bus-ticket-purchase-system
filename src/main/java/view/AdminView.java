package view;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class AdminView {
    private final ManagerService managerService = new ManagerService();
    private final TicketService ticketService = new TicketService();
    private final CompanyService companyService = new CompanyService();
    private final BusService busService = new BusService();
    private final Scanner scanner = new Scanner(System.in);

    public void loginAdmin() {
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
                        Main.printInvalidInput();
                }
            }
            // TODO : belows :)
            //تعداد بلیط های فروخته شده
            // تعداد صندلی های باقیمانده بر اساس نوع اتوبوس
            // به ترتیب تاریخ حرکت
        } else
            System.out.println("incorrect username or password!");
    }

    private void addNewInformation() {
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
                try {
                    addNewTicket();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Main.printInvalidInput();
        }
    }

    private void addNewCompany() {
        System.out.print("company name: ");
        String name = scanner.nextLine();
        Company company = new Company();
        company.setName(name);
        companyService.save(company);
    }

    private void addNewBus() {
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
                Main.printInvalidInput();
                System.out.println("...set default type...");
                bus.setType(BusType.REGULAR);
        }
        bus.setTotalSeats(seats);
        bus.setSeatsRemaining(seats);
        busService.save(bus);
    }

    private void addNewTicket() throws ParseException {
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
                    Main.printInvalidInput();
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.print("enter departure time(yyyy-MM-dd hh:mm:ss): ");
        String departureTime = scanner.nextLine();
        System.out.print("enter arrival approximate time(yyyy-MM-dd hh:mm:ss): ");
        String arrivalApproximateTime = scanner.nextLine();

        TicketBuilder ticketBuilder = new TicketBuilder();
        Ticket ticket = ticketBuilder
                .withOrigin(origin)
                .withDestination(destination)
                .withCost(cost)
                .withDepartureTime(simpleDateFormat.parse(departureTime))
                .withArrivalApproximateTime(simpleDateFormat.parse(arrivalApproximateTime))
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

    private void showInformation() {//TODO

    }
}
