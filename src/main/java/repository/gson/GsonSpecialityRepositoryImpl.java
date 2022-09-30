package repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Specialty;
import model.Status;
import repository.SpecialityRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GsonSpecialityRepositoryImpl implements SpecialityRepository {
    private final static String specialityJson = "src/main/resources/specialties.json";
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Specialty> getAllSpecialities() {
        try {
            Reader reader = new FileReader(specialityJson);
            Type specialityListType = new TypeToken<ArrayList<Specialty>>(){}.getType();
            List<Specialty> specialtyList = gson.fromJson(reader, specialityListType);
            return Objects.requireNonNullElseGet(specialtyList, ArrayList::new);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
    private void writeSpecialitiesToFile(List<Specialty> specialties) {
        try(Writer writer = new FileWriter(specialityJson)) {
            gson.toJson(specialties, writer);
        } catch (IOException e) {
            System.out.println("IN writeSpecialitiesToFile - error occurred: " + e.getMessage());
        }
    }
    private Integer generateId(List<Specialty> specialties) {
        Specialty maxIdSpecialities = specialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(maxIdSpecialities) ? maxIdSpecialities.getId() + 1 : 1;
    }
    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> currentSpecialities = getAllSpecialities();
        specialty.setId(generateId(currentSpecialities));
        currentSpecialities.add(specialty);
        writeSpecialitiesToFile(currentSpecialities);
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        return getAllSpecialities();
    }

    @Override
    public Specialty getOne(Integer id) {
        return getAllSpecialities().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Specialty update(Specialty specialtyToUpdate) {
        List<Specialty> specialtyList = getAllSpecialities();
        for (Specialty currentSpeciality : specialtyList) {
            if(currentSpeciality.getId().equals(specialtyToUpdate.getId())) {
                currentSpeciality.setSpeciality(specialtyToUpdate.getSpeciality());
                currentSpeciality.setStatus(specialtyToUpdate.getStatus());
            }
        }
        writeSpecialitiesToFile(specialtyList);
        return specialtyToUpdate;
    }

    @Override
    public void removeById(Integer id) {
        List<Specialty> currentSpecialties = getAllSpecialities();
        for (Specialty currentSpeciality: currentSpecialties) {
            if(currentSpeciality.getId().equals(id)) {
                currentSpeciality.setStatus(Status.DELETED);
            }
        }
        writeSpecialitiesToFile(currentSpecialties);
    }
}
