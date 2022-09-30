package model;


public class Skill {
    private Integer id;
    private String name;
    private Status status;

    public Skill() {
    }

    public Skill(Integer id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Skill: " +
                "id=" + id +
                "| name='" + name + '\'' +
                "| status=" + status +
                "\n";
    }
}
