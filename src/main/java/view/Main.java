package view;

import service.ManagerService;
import util.CreateScanner;

import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class Main {
    private static final ManagerService managerService = new ManagerService();
    private static final Scanner scanner = CreateScanner.getInstance();
    private static final AdminView adminView = new AdminView();
    private static final UserView userView = new UserView();

    public static void main(String[] args) {
        //managerService.init();

        System.out.print("Hi ^_^ welcome to our online bus ticket...\nlogin as 1.admin or 2.user?(1/2): ");
        String loginAnswer = scanner.nextLine();
        switch (loginAnswer) {
            case "1":
                adminView.loginAdmin();
                break;
            case "2":
                userView.loginUser();
                break;
            default:
                printInvalidInput();
        }
    }

    static void printInvalidInput() {
        System.out.println("invalid input!");
    }

}
