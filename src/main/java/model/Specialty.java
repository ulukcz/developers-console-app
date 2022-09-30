package model;

public class Specialty {
    private Integer id;
    private String speciality;
    private Status status;
    public  Specialty() {

    }

    public Specialty(Integer id, String speciality, Status status) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
