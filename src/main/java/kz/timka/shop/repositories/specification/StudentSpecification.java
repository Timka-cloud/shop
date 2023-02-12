package kz.timka.shop.repositories.specification;

import kz.timka.shop.entities.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentSpecification {

    public static Specification<Student> scoreGreaterOrEqualsThan(Integer score) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("score"), score);
    }

    public static Specification<Student> scoreLessOrEqualsThan(Integer score) {
         return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("score"), score);

    }

    // select s from Student s where s.name like %Bo%
    public static Specification<Student> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name));
    }
}
