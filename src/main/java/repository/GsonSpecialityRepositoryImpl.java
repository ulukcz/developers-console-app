package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Specialty;
import model.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonSpecialityRepositoryImpl implements SpecialityRepository {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void add(Specialty specialty) {
        List<Specialty> listSpec = getAll();
        listSpec.add(specialty);
        try {
            Writer writer = new FileWriter(specialityJson);
            gson.toJson(listSpec, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Specialty> getAll() {
        try {
            Reader reader = new FileReader(specialityJson);
            Type specialtyListType = new TypeToken<ArrayList<Specialty>>(){}.getType();
            List<Specialty> specialtyList = gson.fromJson(reader, specialtyListType);
            return Objects.requireNonNullElseGet(specialtyList, ArrayList::new);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Specialty getOne(Long aLong) {
        List<Specialty> developerSpecialities = getAll();
        for (Specialty dev:developerSpecialities) {
            if (dev.getId() == aLong) {
                return dev;
            }
        }
        return null;
    }

    @Override
    public void update(Specialty oldT, Specialty newT) throws IOException {
        List<Specialty> developerSpecialities = getAll();
        for (Specialty dev: developerSpecialities) {
            if (dev.getId() == oldT.getId()) {
                dev.copy(newT);
            }
        }
        save(developerSpecialities, new FileWriter(specialityJson));
    }

    @Override
    public void remove(Long aLong) throws IOException {
        Specialty developerSpeciality = getOne(aLong);
        developerSpeciality.setStatus(Status.DELETED);
        update(getOne(aLong), developerSpeciality);
    }

    @Override
    public void save(List<Specialty> specialtyList, FileWriter writer) {
        try(writer) {
            gson.toJson(specialtyList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
