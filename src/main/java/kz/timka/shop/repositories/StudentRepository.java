package kz.timka.shop.repositories;

import kz.timka.shop.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("from Student s order by s.id asc")
    List<Student> findAllOrdered();
}
