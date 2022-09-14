package controller;

import model.Specialty;
import model.Status;
import repository.GsonSpecialityRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class SpecialityController extends GsonSpecialityRepositoryImpl {
        public List<Specialty> getActive() {
            List<Specialty> activeDevSpeciality = new ArrayList<>();
            for (Specialty devSpeciality : getAll()) {
                if (devSpeciality.getStatus() == Status.ACTIVE)
                    activeDevSpeciality.add(devSpeciality);
            }
            return activeDevSpeciality;
        }
}
