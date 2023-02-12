package kz.timka.shop.dto;

import kz.timka.shop.entities.Student;

public class StudentDTO {
    private Long id;

    private String name;

    private Integer score;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.score = student.getScore();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
