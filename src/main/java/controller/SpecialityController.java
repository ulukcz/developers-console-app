package controller;

import model.Specialty;
import model.Status;
import repository.SpecialityRepository;
import repository.gson.GsonSpecialityRepositoryImpl;

public class SpecialityController {
    private final SpecialityRepository specialityRepository = new GsonSpecialityRepositoryImpl();
    private final DeveloperController developerController = new DeveloperController();
    public Specialty createSpecialty(String name) {
        Specialty specialty = new Specialty();
        specialty.setSpeciality(name);
        specialty.setStatus(Status.ACTIVE);

        return specialityRepository.save(specialty);
    }
    public Specialty editSpecialty(Integer id, String name) {
        Specialty specialty = specialityRepository.getOne(id);
        specialty.setSpeciality(name);

        return specialityRepository.update(specialty);
    }

    public Specialty removeSpeciality(Integer idDev) {
        Specialty specialty = specialityRepository.getOne(idDev);
        specialityRepository.removeById(idDev);
        return specialty;
    }

    public Specialty getSpecialityByID(Integer id) {
        if (developerController.getActiveDeveloperByID(id) == null)
            return null;
        else {
            return switch (specialityRepository.getOne(id).getStatus()) {
                case ACTIVE -> specialityRepository.getOne(id);
                case DELETED -> null;
            };
        }
    }
}
