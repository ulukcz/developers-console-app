package controller;

import model.Developer;
import model.Status;
import repository.DeveloperRepository;
import repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer createDeveloper(String firstName, String lastName) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setStatus(Status.ACTIVE);

        return developerRepository.save(developer);
    }
    public Developer editDeveloper(Integer id, String firstName, String lastName) {
        Developer developer = developerRepository.getOne(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);

        return developerRepository.update(developer);
    }

    public Developer removeDeveloper(Integer idDev) {
        Developer developer = developerRepository.getOne(idDev);
        developerRepository.removeById(idDev);
        return developer;
    }

    public List<Developer> allActiveDevelopers() {
        return developerRepository.getAll().stream().
                filter(developer -> developer.getStatus().equals(Status.ACTIVE)).toList();
    }

    public Developer getActiveDeveloperByID(Integer id) {
        return switch (developerRepository.getOne(id).getStatus()) {
            case ACTIVE -> developerRepository.getOne(id);
            case DELETED -> null;
        };
    }
}
