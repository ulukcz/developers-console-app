package model;

import java.util.List;

public class Skill {
    private int id;
    private List<String> skills;
    private Status status;

    public Skill(int id, List<String> skills, Status status) {
        this.id = id;
        this.skills = skills;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill: " +
                "id=" + id +
                "| skills='" + skills + '\'' +
                "| status=" + status +
                "\n";
    }

    public void copy(Skill newT) {
        this.setId(newT.getId());
        this.setSkills(newT.getSkills());
        this.setStatus(newT.getStatus());
    }
}
