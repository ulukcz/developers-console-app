package view;

import controller.SpecialityController;
import model.Specialty;

import java.util.Scanner;

public class SpecialityView {
    private final Scanner scanner = new Scanner(System.in);
    private final SpecialityController specialityController = new SpecialityController();

    public void createSpeciality() {
        System.out.println("Enter your speciality: ");
        String specialityName = scanner.nextLine();

        specialityController.createSpecialty(specialityName);
    }
    public void editSpeciality() {
        Integer idSpeciality = getDeveloperId();
        System.out.println("Enter your new speciality:");
        String specialityName = scanner.nextLine();

        Specialty createdSpeciality = specialityController.editSpecialty(idSpeciality, specialityName);
        System.out.println("Speciality changed to " + createdSpeciality.getSpeciality());
    }
    public Integer getDeveloperId() {
        System.out.println("Enter developer id: ");
        int idDeveloper;
        while (true) {
            try {
                idDeveloper = scanner.nextInt();
                scanner.nextLine();
                if (specialityController.getSpecialityByID(idDeveloper) != null)
                    break;
                else {
                    System.out.println("Speciality with id #" + idDeveloper + " doesn't exist. Please repeat.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input or number of speciality!");
            }
        }
        return idDeveloper;
    }

    public void removeSpeciality() {
        Integer idDev = getDeveloperId();
        Specialty removedSpeciality = specialityController.removeSpeciality(idDev);

        System.out.printf("Speciality %s was removed.\n", removedSpeciality.getSpeciality());
    }
    public Specialty specialtyById(Integer id) {
        return specialityController.getSpecialityByID(id);
    }
}
