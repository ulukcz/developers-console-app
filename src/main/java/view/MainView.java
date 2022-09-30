package view;

import java.util.Scanner;

public class MainView {
    private final Scanner scanner= new Scanner(System.in);
    private final SkillView skillView = new SkillView();
    private final DeveloperView developerView = new DeveloperView();
    private final SpecialityView specialityView = new SpecialityView();

    public void showMainMenu () {
        String COMMANDS_MENU = """
                    0 - Exit
                    1 - Create dev
                    2 - Edit dev name or last name
                    3 - Edit dev skills
                    4 - Edit dev speciality
                    5 - remove dev
                    6 - remove speciality
                    7 - remove skills
                    8 - list developers
                    9 - help main commands""";
        System.out.println(COMMANDS_MENU);
        while (true) {
            try {
                System.out.println("Enter main command number: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> developerView.createDeveloper();
                    case 2 -> developerView.editDeveloper();
                    case 3 -> skillView.editSkills();
                    case 4 -> specialityView.editSpeciality();
                    case 5 -> developerView.removeDeveloper();
                    case 6 -> specialityView.removeSpeciality();
                    case 7 -> skillView.removeSkills();
                    case 8 -> developerView.listDevelopers();
                    case 9 -> System.out.println(COMMANDS_MENU);
                    case 0 -> {
                        System.out.println("Good bye!!!");
                        System.exit(0);
                    }
                    default -> throw new IllegalStateException();
                }
            } catch (Exception e) {
                System.out.println("Please repeat correct command!");
                scanner.nextLine();
            }
        }

    }
}
