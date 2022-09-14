package model;

import java.util.List;

public class Developer {
    private int id;
    private String firstName;
    private String lastName;
    private Status status;

    public Developer(int id, String firstName, String lastName, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void copy(Developer fromDev) {
        this.setId(fromDev.getId());
        this.setFirstName(fromDev.getFirstName());
        this.setLastName(fromDev.getLastName());
        this.setStatus(fromDev.getStatus());
    }

    @Override
    public String toString() {
        return "Developer: " +
                "id=" + id +
                "| firstName='" + firstName + '\'' +
                "| lastName='" + lastName + '\'' +
                "| status=" + status +
                "\n";
    }
}