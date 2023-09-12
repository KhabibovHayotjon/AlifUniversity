package uz.alif.lesson_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa2.entity.Address;
import uz.alif.lesson_jpa2.entity.Student;
import uz.alif.lesson_jpa2.entity.University;
import uz.alif.lesson_jpa2.payload.UniversityDto;
import uz.alif.lesson_jpa2.repository.AddressRepository;
import uz.alif.lesson_jpa2.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;


    @RequestMapping(value = "/AddUniversity", method = RequestMethod.POST)
    public String AddUniversity(@RequestBody UniversityDto universityDto){
        Address address = new Address(universityDto.getRegion(), universityDto.getDistrict(), universityDto.getStreet());
        Address saveAdress = addressRepository.save(address);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(saveAdress);
        universityRepository.save(university);
        return "University saved";
    }

    @RequestMapping(value = "/AllUniversity", method = RequestMethod.GET)
    public List<University> getAllUniversity(){
        return universityRepository.findAll();
    }



    @RequestMapping(value = "/getOneUniversity/{id}", method = RequestMethod.GET)
    public University GetOneUniversity(@PathVariable int id){
        Optional<University> optionalUniversity  = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            return optionalUniversity.get();
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/editUniversity/{id}", method = RequestMethod.PUT)
    public String EditUniversity(@PathVariable int id, @RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty())
            return "University not found";
        University university = optionalUniversity.get();
        university.setName(universityDto.getName());

        Address address = university.getAddress();
        address.setDistrict(universityDto.getDistrict());
        address.setRegion(universityDto.getRegion());
        address.setStreet(universityDto.getStreet());
        universityRepository.save(university);
        addressRepository.save(address);
        return "University update";
    }

    @RequestMapping(value = "/delUniversityId/{id}", method = RequestMethod.DELETE)
    public String DelById(@PathVariable int id){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty())
            return "not found";

        addressRepository.deleteById(optionalUniversity.get().getAddress().getId());
        universityRepository.deleteById(id);

        return "university deleted";
    }

}
