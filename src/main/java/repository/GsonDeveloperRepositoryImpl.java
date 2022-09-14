package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Specialty;
import model.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private Developer developer;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void add(Developer developer) {
        List<Developer> listDev = getAll();
        listDev.add(developer);
        try {
            Writer writer = new FileWriter(developerJson);
            gson.toJson(listDev, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Developer added");
    }

    @Override
    public List<Developer> getAll() {
        try {
            Reader reader = new FileReader(developerJson);
            Type developerListType = new TypeToken<ArrayList<Developer>>(){}.getType();
            List<Developer> developerList = gson.fromJson(reader, developerListType);
            return Objects.requireNonNullElseGet(developerList, ArrayList::new);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return new ArrayList<Developer>();
        }
    }

    @Override
    public Developer getOne(Long aLong) {
        List<Developer> developerList = getAll();
        for (Developer dev:developerList) {
            if (dev.getId() == aLong) {
                return dev;
            }
        }
        return null;
    }

    @Override
    public List<Developer> findBySpeciality(Specialty specialty) {
        List<Developer> developerListBySpeciality = new ArrayList<>();
        List<Specialty> specialtyList = new ArrayList<>();
        List<Developer> developerList = getAll();

        return developerListBySpeciality;
    }

    @Override
    public List<Developer> findBySkill(String skill) {
        return null;
    }

    @Override
    public void update(Developer oldT, Developer newT) throws IOException {
        List<Developer> developerList = getAll();
        for (Developer dev: developerList) {
            if (dev.getId() == oldT.getId()) {
                dev.copy(newT);
            }
        }
        save(developerList, new FileWriter(developerJson));
    }

    @Override
    public void remove(Long aLong) throws IOException {
        Developer developer = getOne(aLong);
        developer.setStatus(Status.DELETED);
        update(getOne(aLong), developer);
        System.out.println("Deleted");
    }

    @Override
    public void save(List<Developer> developerList, FileWriter writer) {
        try(writer) {
            gson.toJson(developerList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> allId() {
        List<Integer> ids = new ArrayList<>();
        for (Developer dev:getAll()) {
            ids.add(dev.getId());
            }
        return ids;
    }
}
