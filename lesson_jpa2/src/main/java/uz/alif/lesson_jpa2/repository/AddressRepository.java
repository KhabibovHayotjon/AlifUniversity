package uz.alif.lesson_jpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa2.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
