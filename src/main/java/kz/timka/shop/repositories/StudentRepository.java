package kz.timka.shop.repositories;

import kz.timka.shop.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    //@Query("select s from Student s where s.score between ?1 and ?2 order by s.id")
    List<Student> findAllStudentByScoreBetweenOrderById(Integer min, Integer max);
}
