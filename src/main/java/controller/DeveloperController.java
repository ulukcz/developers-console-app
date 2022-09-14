package controller;

import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;
import repository.GsonDeveloperRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperController extends GsonDeveloperRepositoryImpl {
    private final Scanner scan = new Scanner(System.in);
    private final SpecialityController specialityController = new SpecialityController();
    private final SkillController skillController = new SkillController();
    public void addDeveloper() {
        System.out.println("Please enter the first name:");
        String name = scan.nextLine();
        System.out.println("Please enter the last name:");
        String lastName = scan.nextLine();
        System.out.println("Please enter speciality:");
        String speciality = scan.nextLine();
        System.out.println("Please enter skills (comma separated):");
        String[] skills = scan.nextLine().split(",");
        int id = setUniqueId();
        Skill skill = new Skill(id, List.of(skills), Status.ACTIVE);
        Specialty specialty = new Specialty(id, speciality, Status.ACTIVE);
        Developer newDev = new Developer(id, name, lastName, Status.ACTIVE);
        specialityController.add(specialty);
        skillController.add(skill);
        add(newDev);
    }

    public void editDeveloper() {
        Long id = getCorrectId();
        Developer developer = getOne(id);
        Specialty devSpec = specialityController.getOne(id);
        Skill devSkills = skillController.getOne(id);
        System.out.println("What do you want to edit? Please enter name/lastname/skill/speciality: ");
        scan.nextLine();
        boolean doIt = true;
        while (doIt){
            String infForEdit = scan.nextLine();
            switch (infForEdit) {
                case "name" -> {
                    System.out.println("Please enter new first name:");
                    String name = scan.nextLine();
                    developer.setFirstName(name);
                    try {
                        update(getOne(id), developer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Developer first name with ID #" + id + " was edited");
                    doIt = false;
                }
                case "lastname" -> {
                    System.out.println("Please enter new last name:");
                    String lastname = scan.nextLine();
                    developer.setLastName(lastname);
                    try {
                        update(getOne(id), developer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Developer last name with ID #" + id + " was edited");
                    doIt = false;
                }
                case "speciality" -> {
                    System.out.println("Please enter new speciality:");
                    String speciality = scan.nextLine();
                    devSpec.setSpeciality(speciality);
                    try {
                        specialityController.update(specialityController.getOne(id), devSpec);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Developer speciality with ID #" + id + " was edited");
                    doIt = false;
                }
                case "skill" -> {
                    System.out.println("Please enter new skills:");
                    String[] skills = scan.nextLine().split(",");
                    devSkills.setSkills(List.of(skills));
                    try {
                        skillController.update(skillController.getOne(id), devSkills);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Developer skills with ID #" + id + " was edited");
                    doIt = false;
                }
                default -> System.out.println("Please enter correctly (name/lastname/skill/speciality)!");
            }
        }
    }

    public List<Developer> activeDevelopers() {
        List<Developer> activeDevelopers = new ArrayList<>();
        for (Developer dev: getAll()) {
            if (dev.getStatus() == Status.ACTIVE)
                activeDevelopers.add(dev);
        }
        return activeDevelopers;
    }
    public void viewActiveDevelopers() {
        List<Developer> activeDevelopers = activeDevelopers();
        List<Skill> skills = skillController.getActive();
        List<Specialty> specialityList = specialityController.getActive();
        System.out.println("|-ID-|-First-Name--|---Last-Name--|-Speciality-|----Skills----|STATUS|");
        if (activeDevelopers().size() == 0)
            System.out.println("|-----------------------NO--ACTIVE--DEVELOPERS-----------------------|");
        else {
            for (int i = 0; i < activeDevelopers.size(); i++) {
                System.out.printf("| %d | %s | %s | %s | %s |%s|\n",
                        activeDevelopers.get(i).getId(), activeDevelopers.get(i).getFirstName(), activeDevelopers.get(i).getLastName(),
                        specialityList.get(i).getSpeciality(), skills.get(i).getSkills(), skills.get(i).getStatus());
            }
        }
        System.out.println("|----|-------------|--------------|------------|--------------|------|");
    }

    public void removeDeveloper() {
        Long id = getCorrectId();
        try {
            remove(id);
            skillController.remove(id);
            specialityController.remove(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int setUniqueId() {
        int sizeList = allId().size();
        if (sizeList == 0)
            return 0;
        else
            return allId().get(sizeList - 1) + 1;
    }
    public Long getCorrectId() {
        System.out.println("Please enter developer Id:");
        while (true) {
            try {
                long id = scan.nextLong();
                for (Developer dev: activeDevelopers())
                    if (dev.getId() == id)
                        return id;
                System.out.println("There is no developer with ID #" + id + ". Please repeat!");
            } catch (Exception e) {
                System.out.println("Please enter just numbers!");
                scan.nextLine();
            }
        }
    }
    public void helpList() {
        System.out.println(
                """
                        - add - Adding developer
                        - edit - editing developer
                        - remove - removing developer
                        - list - view all active developers
                        - help - commands list
                        - exit - closing app""");
    }
}
