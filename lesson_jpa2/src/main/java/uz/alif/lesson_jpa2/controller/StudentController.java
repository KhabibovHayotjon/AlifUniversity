package uz.alif.lesson_jpa2.controller;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa2.entity.Address;
import uz.alif.lesson_jpa2.entity.Group;
import uz.alif.lesson_jpa2.entity.Student;
import uz.alif.lesson_jpa2.entity.Subject;
import uz.alif.lesson_jpa2.payload.StudentDto;
import uz.alif.lesson_jpa2.repository.AddressRepository;
import uz.alif.lesson_jpa2.repository.GroupRepository;
import uz.alif.lesson_jpa2.repository.StudentRepository;
import uz.alif.lesson_jpa2.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    public String AddStudent(@RequestBody StudentDto studentDto){
        boolean exits = studentRepository.existsByNameAndGroup_Id(studentDto.getName(),studentDto.getGroup_id());
        if (exits)
            return "this group has this student";
        Address address = new Address(studentDto.getRegion(), studentDto.getDistrict(), studentDto.getStreet());
        Address saveAdress = addressRepository.save(address);
        Student student1 = new Student();
        student1.setName(studentDto.getName());
        student1.setLastname(studentDto.getLastname());


        ArrayList<int[]> subject1 = new ArrayList<>();



        Optional<Group> groupOptional = groupRepository.findById(studentDto.getGroup_id());
        if (groupOptional.isEmpty())
            return "group not found";
        student1.setGroup(groupOptional.get());
        student1.setAddress(saveAdress);
        studentRepository.save(student1);
        return "added";
    }

    @GetMapping
    public Page<Student> getAll(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        return studentRepository.findAll(pageable);
    }

    @GetMapping(".hello/{id}")
    public Page<Student> getByUnivId(@RequestParam int page,@PathVariable int id){
        Pageable pageable = PageRequest.of(page, id);
        return studentRepository.findAllByGroup_Faculity_UniversityId(id,pageable);
    }

}
