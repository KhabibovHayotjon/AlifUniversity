package uz.alif.lesson_jpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.lesson_jpa2.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
    boolean existsById(Integer id);

}
