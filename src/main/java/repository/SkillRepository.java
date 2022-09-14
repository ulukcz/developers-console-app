package repository;

import model.Developer;
import model.Skill;

public interface SkillRepository extends GenericRepository<Skill, Long>{
    String skillJson = "src/main/resources/skills.json";
}
