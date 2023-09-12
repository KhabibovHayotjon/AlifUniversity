package uz.alif.lesson_jpa2.repository;

import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alif.lesson_jpa2.entity.Faculity;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Faculity, Integer> {

   boolean existsByNameAndUniversityId(String name, Integer university_id);
   boolean existsByName(String name);
//method query
   List<Faculity>findAllByUniversityId(Integer university_id);

   //jpaQuery
   @Query(value = "select f from Faculity f where f.university.id=:university_id")
   List<Faculity>hamasiniolishjpaquery(int university_id);

   //nativ Query
   //peshi * ba agijo giftagesha dodan darkor!
   @Query(value = "select f.* from faculity f join university u on f.university_id=u.id where u.id=:universityId",nativeQuery = true)
   List<Faculity>hamasiniolishnativeQuery(int universityId);
}
