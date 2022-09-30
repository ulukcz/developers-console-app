package view;

import controller.DeveloperController;
import model.Developer;
import model.Skill;
import model.Specialty;

import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final Scanner scanner = new Scanner(System.in);
    private final DeveloperController developerController = new DeveloperController();
    private final SpecialityView specialityView = new SpecialityView();
    private final SkillView skillView = new SkillView();
    public void createDeveloper() {
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        specialityView.createSpeciality();
        skillView.createSkill();

        Developer createdDeveloper = developerController.createDeveloper(firstName, lastName);
        System.out.println("Created developer " + createdDeveloper.toString());
    }
    public void editDeveloper() {
        Integer idDev = getDeveloperId();
        System.out.println("Enter new first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter new last name: ");
        String lastName = scanner.nextLine();

        Developer editedDeveloper = developerController.editDeveloper(idDev, firstName, lastName);
        System.out.println("Edited developer " + editedDeveloper.toString());
    }

    public void removeDeveloper() {
        Integer idDev = getDeveloperId();
        Developer removedDeveloper = developerController.removeDeveloper(idDev);

        System.out.printf("Developer %s %s was removed.\n", removedDeveloper.getFirstName(), removedDeveloper.getLastName());
    }
    public Integer getDeveloperId() {
        System.out.println("Enter developer id: ");
        int idDeveloper;
        while (true) {
            try {
                idDeveloper = scanner.nextInt();
                scanner.nextLine();
                if (developerController.getActiveDeveloperByID(idDeveloper) != null)
                    break;
                else {
                    System.out.println("Developer with id #" + idDeveloper + " doesn't exist. Please repeat.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input or number of developer!");
            }
        }
        return idDeveloper;
    }

    public void listDevelopers() {
        List<Developer> developerList = developerController.allActiveDevelopers();
        for (Developer dev: developerList) {
            Specialty specialty = specialityView.specialtyById(dev.getId());
            List<Skill> skills = skillView.activeSkillsById(dev.getId());
            System.out.printf("%d. %s %s, ", dev.getId(), dev.getFirstName(), dev.getLastName());
            if (specialty == null)
                System.out.print("No Speciality, ");
            else
                System.out.printf("%s, ", specialityView.specialtyById(dev.getId()).getSpeciality());
            System.out.print("( ");
            if (skills.size() == 0)
                System.out.print("No Skills ");
            else
                for (Skill skill: skillView.activeSkillsById(dev.getId())) {
                    System.out.printf("%s ", skill.getName());
                }
            System.out.print(")\n");
        }
    }
}