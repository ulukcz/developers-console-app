package view;

import controller.DeveloperController;

import java.util.Scanner;
public class DeveloperView extends DeveloperController {
    private final Scanner scan = new Scanner(System.in);

    public void start() {
        viewActiveDevelopers();
        while (true) {
            try {
                String input = scan.nextLine();
                if (input.equals("exit"))
                    break;
                switch (input) {
                    case "add" -> addDeveloper();
                    case "remove" -> removeDeveloper();
                    case "edit" -> editDeveloper();
                    case "list" -> viewActiveDevelopers();
                    case "help" -> helpList();
                    default -> throw new IllegalStateException();
                }
            } catch (Exception e) {
                System.out.println("Please repeat correct command!");
            }
        }
    }
}
