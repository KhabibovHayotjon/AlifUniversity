package uz.alif.lesson_jpa2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.lesson_jpa2.entity.Student;
import uz.alif.lesson_jpa2.entity.Subject;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    boolean existsByNameAndGroup_Id(String name, Integer group_id);

    Page<Student> findAllByGroup_Faculity_UniversityId(Integer group_faculity_university_id, Pageable pageable);

}
