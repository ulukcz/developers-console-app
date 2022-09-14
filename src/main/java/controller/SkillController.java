package controller;

import model.Skill;
import model.Status;
import repository.GsonSkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class SkillController extends GsonSkillRepositoryImpl {
    public List<Skill> getActive() {
        List<Skill> activeDevSkills = new ArrayList<>();
        for (Skill devSkill: getAll()) {
            if (devSkill.getStatus() == Status.ACTIVE)
                activeDevSkills.add(devSkill);
        }
        return activeDevSkills;
    }
}
