package kz.timka.shop.services;

import kz.timka.shop.entities.Student;
import kz.timka.shop.exceptions.ResourceNotFoundException;
import kz.timka.shop.repositories.StudentRepository;
import kz.timka.shop.repositories.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> find(Integer minScore, Integer maxScore, String partName, Integer page) {
        Specification<Student> spec = Specification.where(null);
        // select s from Student s where true
        if(minScore != null) {
            spec = spec.and(StudentSpecification.scoreGreaterOrEqualsThan(minScore));
            // select s from Student s where true and s.score >= minScore
        }
        if(maxScore != null) {
            spec = spec.and(StudentSpecification.scoreLessOrEqualsThan(maxScore));
            // select s from Student s where true and s.score >= minScore and s.score <= maxScore
        }
        if(partName != null) {
            spec = spec.and(StudentSpecification.nameLike(partName));
            // select s from Student s where true and s.score >= minScore and s.score <= maxScore and s.name like %partName%

        }
        return studentRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void changeScore(Long studentId, Integer delta) {

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to change score, student not found by id: " + studentId));
        student.setScore(student.getScore() + delta);


    }
}
