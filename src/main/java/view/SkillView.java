package view;

import controller.SkillController;
import model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {

    private final Scanner scanner = new Scanner(System.in);
    private final SkillController skillController = new SkillController();
    public void createSkill() {
        while (true) {
            System.out.println("Enter skill name (if skills complete, type 0): ");
            String name = scanner.nextLine();
            if (name.equals("0"))
                break;
            skillController.createSkill(name);
        }
    }
    public void editSkills() {
        Integer idSkill = getDeveloperId();
        skillController.removeSkillsByIdFromJson(idSkill);
        while (true) {
            System.out.println("Enter new skill name (if skills complete, type 0): ");
            String name = scanner.nextLine();
            if (name.equals("0"))
                break;
            skillController.editSkill(idSkill, name);
        }
        System.out.println("Skills changed");
    }

    public void removeSkills() {
        Integer idSkill = getDeveloperId();
        List<Skill> activeSkills = skillController.getActiveSkillsByID(idSkill);
        if (activeSkills.size() != 0) {
            skillController.removeSkills(idSkill);
            System.out.printf("Skills Developer #%d removed\n", idSkill);
        } else {
            System.out.println("Skills not active.");
        }
    }
    public Integer getDeveloperId() {
        System.out.println("Enter developer id: ");
        int idDeveloper;
        while (true) {
            try {
                idDeveloper = scanner.nextInt();
                scanner.nextLine();
                List<Skill> skill = skillController.getActiveSkillsByID(idDeveloper);
                if (skill.size() != 0)
                    break;
                else {
                    System.out.println("Skills with id #" + idDeveloper + " doesn't exist. Please repeat.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input or number of skills!");
            }
        }
        return idDeveloper;
    }
    public List<Skill> activeSkillsById(Integer id) {
        return  skillController.getActiveSkillsByID(id);
    }
}
