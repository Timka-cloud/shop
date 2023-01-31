package kz.timka.shop.services;

import kz.timka.shop.entities.Student;
import kz.timka.shop.exceptions.ResourceNotFoundException;
import kz.timka.shop.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Student> getAllStudents() {
        return studentRepository.findAllOrdered();
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
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
