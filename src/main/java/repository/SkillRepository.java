package repository;

import model.Skill;

public interface SkillRepository extends GenericRepository<Skill, Integer>{
    void removeListSkills(Integer id);
}
