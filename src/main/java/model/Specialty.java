package model;

public class Specialty {
    private int id;
    private String speciality;
    private Status status;

    public Specialty(int id, String speciality, Status status) {
        this.id = id;
        this.speciality = speciality;
        this.status = status;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void copy(Specialty newT) {
        this.setId(newT.getId());
        this.setSpeciality(newT.getSpeciality());
        this.setStatus(newT.getStatus());
    }
}
