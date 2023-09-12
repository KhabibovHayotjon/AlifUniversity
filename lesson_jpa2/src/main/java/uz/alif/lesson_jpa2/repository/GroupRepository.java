package uz.alif.lesson_jpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alif.lesson_jpa2.entity.Group;
import uz.alif.lesson_jpa2.entity.University;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    boolean existsByNameAndFaculityId(String name, Integer faculity_id);
    boolean existsByName(String name);

    List<Group> findAllByFaculity_UniversityId(Integer faculity_university_id);




    @Query(value = "select g from groups g where g.faculity .university.id=:id")
    List<Group>jpafindAllById(int id);

    @Query(value = "select g.* from groups g join faculity f on f.id=g.faculity_id join university u on f.university_id=u.id where u.id=:id",nativeQuery = true)
    List<Group>nativefindAllById(int id);

}
