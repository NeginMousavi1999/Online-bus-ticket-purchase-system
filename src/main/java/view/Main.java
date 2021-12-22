package view;

import util.CreateScanner;

import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class Main {
    private static final Scanner scanner = CreateScanner.getInstance();
    private static final AdminView adminView = new AdminView();
    private static final UserView userView = new UserView();

    public static void main(String[] args) {

        System.out.println("Hi ^_^ welcome to our online bus ticket...");
        loop:
        while (true) {
            System.out.print("login as 1.admin or 2.user? and 3.exit(1/2/3): ");
            String loginAnswer = scanner.nextLine();
            switch (loginAnswer) {
                case "1":
                    adminView.loginAdmin();
                    break;
                case "2":
                    userView.loginUser();
                    break;
                case "3":
                    System.out.println("bye bye");
                    break loop;
                default:
                    printInvalidInput();
            }
        }
    }

    static void printInvalidInput() {
        System.out.println("invalid input!");
    }
}
