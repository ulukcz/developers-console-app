package repository;

import model.Developer;
import model.Specialty;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    List<Developer> findBySpeciality(Specialty specialty);
    List<Developer> findBySkill(String skill);
    String developerJson = "src/main/resources/developers.json";
}
