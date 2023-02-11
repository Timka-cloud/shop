package kz.timka.shop.controllers;

import kz.timka.shop.entities.Student;
import kz.timka.shop.exceptions.ResourceNotFoundException;
import kz.timka.shop.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(
            @RequestParam(name = "min_price",defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "p", defaultValue = "1") Long page) {
        return studentService.getAllWithFilterStudents(minPrice, maxPrice, page);
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found by id: " + id));
    }

    @GetMapping("/students/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/students/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeScore(studentId, delta);
    }



}
