package repository;

import model.Specialty;

public interface SpecialityRepository extends GenericRepository<Specialty, Long>{
    String specialityJson = "src/main/resources/specialties.json";
}
