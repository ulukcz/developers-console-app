package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonSkillRepositoryImpl implements SkillRepository{
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void add(Skill skill) {
        List<Skill> listSpec = getAll();
        listSpec.add(skill);
        try {
            Writer writer = new FileWriter(skillJson);
            gson.toJson(listSpec, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getAll() {
        try {
            Reader reader = new FileReader(skillJson);
            Type skillListType = new TypeToken<ArrayList<Skill>>(){}.getType();
            List<Skill> skillList = gson.fromJson(reader, skillListType);
            return Objects.requireNonNullElseGet(skillList, ArrayList::new);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Skill getOne(Long aLong) {
        List<Skill> developerSkillList = getAll();
        for (Skill dev:developerSkillList) {
            if (dev.getId() == aLong) {
                return dev;
            }
        }
        return null;
    }

    @Override
    public void update(Skill oldT, Skill newT) throws IOException {
        List<Skill> developerSkillList = getAll();
        for (Skill dev: developerSkillList) {
            if (dev.getId() == oldT.getId()) {
                dev.copy(newT);
            }
        }
        save(developerSkillList, new FileWriter(skillJson));
    }

    @Override
    public void remove(Long aLong) throws IOException {
        Skill developerSkill = getOne(aLong);
        developerSkill.setStatus(Status.DELETED);
        update(getOne(aLong), developerSkill);
    }

    @Override
    public void save(List<Skill> skills, FileWriter writer) {
        try(writer) {
            gson.toJson(skills, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
