package repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Skill;
import model.Status;
import repository.DeveloperRepository;
import repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final static String skillJson = "src/main/resources/skills.json";
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    private List<Skill> getAllSkills() {
        try {
            Reader reader = new FileReader(skillJson);
            Type skillListType = new TypeToken<ArrayList<Skill>>(){}.getType();
            List<Skill> skillList = gson.fromJson(reader, skillListType);
            return Objects.requireNonNullElseGet(skillList, ArrayList::new);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private void writeSkillToFile(List<Skill> skills) {
        try(Writer writer = new FileWriter(skillJson)) {
            gson.toJson(skills, writer);
        } catch (IOException e) {
            System.out.println("IN writeSkillToFile - error occurred: " + e.getMessage());
        }
    }

    private Integer generateId(List<Developer> devs) {
        Developer maxIdDev = devs.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDev) ? maxIdDev.getId() + 1 : 1;
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> currentSkills = getAllSkills();
        List<Developer> developerList = developerRepository.getAll();
        skill.setId(generateId(developerList));
        currentSkills.add(skill);
        writeSkillToFile(currentSkills);
        return skill;
    }

    @Override
    public List<Skill> getAll() {
      return  getAllSkills();
    }

    @Override
    public Skill getOne(Integer id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Skill update(Skill skillToUpdate) {
        List<Skill> developerSkillList = getAllSkills();
        developerSkillList.add(skillToUpdate);
        writeSkillToFile(developerSkillList);
        return skillToUpdate;
    }

    @Override
    public void removeById(Integer id) {
        List<Skill> currentSkills = getAllSkills();
        for (Skill currentSkill: currentSkills) {
            if(currentSkill.getId().equals(id)) {
                currentSkill.setStatus(Status.DELETED);
            }
        }
        writeSkillToFile(currentSkills);
    }
    @Override
    public void removeListSkills(Integer id) {
        List<Skill> currentSkills = getAllSkills();
        currentSkills.removeIf(currentSkill -> currentSkill.getId().equals(id));
        writeSkillToFile(currentSkills);
    }
}
