package kz.timka.shop.controllers;

import kz.timka.shop.dto.StudentDTO;
import kz.timka.shop.entities.Student;
import kz.timka.shop.exceptions.ResourceNotFoundException;
import kz.timka.shop.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Page<StudentDTO> getAllStudents(
            @RequestParam(name = "min_score",required = false) Integer minScore,
            @RequestParam(name = "max_score", required = false) Integer maxScore,
            @RequestParam(name = "part_name", required = false) String partName,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if(page < 1) {
            page = 1;
        }

        return studentService.find(minScore, maxScore, partName, page).map(
                s -> new StudentDTO(s)
        );
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found by id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        student.setId(null);
        return studentService.save(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeScore(studentId, delta);
    }



}
