package controller;

import model.Skill;
import model.Status;
import repository.SkillRepository;
import repository.gson.GsonSkillRepositoryImpl;

import java.util.Collections;
import java.util.List;

public class SkillController {

    private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();
    private final DeveloperController developerController = new DeveloperController();

    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setStatus(Status.ACTIVE);

        return skillRepository.save(skill);
    }

    public Skill editSkill(Integer idSkill, String name) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setStatus(Status.ACTIVE);
        skill.setId(idSkill);

        return skillRepository.update(skill);
    }

    public void removeSkillsByIdFromJson(Integer id) {
        skillRepository.removeListSkills(id);
    }

    public void removeSkills(Integer id) {

        skillRepository.removeById(id);
    }

    public List<Skill> getActiveSkillsByID(Integer id) {
        if (developerController.getActiveDeveloperByID(id) == null)
            return Collections.emptyList();
        else {
            return skillRepository.getAll().stream().
                    filter(skill -> skill.getStatus().equals(Status.ACTIVE)).
                    filter(currentSkill -> currentSkill.getId().equals(id)).toList();
        }
    }
}
