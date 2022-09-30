package repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Status;
import repository.DeveloperRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final static String devJson = "src/main/resources/developers.json";
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Developer> getAllDevs() {
        try {
            Reader reader = new FileReader(devJson);
            Type devListType = new TypeToken<ArrayList<Developer>>(){}.getType();
            List<Developer> devList = gson.fromJson(reader, devListType);
            return Objects.requireNonNullElseGet(devList, ArrayList::new);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
    private void writeDevsToFile(List<Developer> devs) {
        try(Writer writer = new FileWriter(devJson)) {
            gson.toJson(devs, writer);
        } catch (IOException e) {
            System.out.println("IN writeDevsToFile - error occurred: " + e.getMessage());
        }
    }
    private Integer generateId(List<Developer> devs) {
        Developer maxIdDev = devs.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDev) ? maxIdDev.getId() + 1 : 1;
    }
    @Override
    public Developer save(Developer developer) {
        List<Developer> currentDevs = getAllDevs();
        developer.setId(generateId(currentDevs));
        currentDevs.add(developer);
        writeDevsToFile(currentDevs);
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevs();
    }

    @Override
    public Developer getOne(Integer id) {
        return getAllDevs().stream().filter(a -> a.getStatus().equals(Status.ACTIVE)).
                filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Developer update(Developer developerToUpdate) {
        List<Developer> developerList = getAllDevs();
        for (Developer currentDev: developerList) {
            if(currentDev.getId().equals(developerToUpdate.getId())) {
                currentDev.setFirstName(developerToUpdate.getFirstName());
                currentDev.setLastName(developerToUpdate.getLastName());
                currentDev.setStatus(developerToUpdate.getStatus());
            }
        }
        writeDevsToFile(developerList);
        return developerToUpdate;
    }

    @Override
    public void removeById(Integer id) {
        List<Developer> currentDevs = getAllDevs();
        for (Developer currentDev: currentDevs) {
            if(currentDev.getId().equals(id)) {
                currentDev.setStatus(Status.DELETED);
            }
        }
        writeDevsToFile(currentDevs);
    }
}
