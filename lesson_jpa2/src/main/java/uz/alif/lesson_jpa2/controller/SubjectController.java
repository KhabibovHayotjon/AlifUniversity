package uz.alif.lesson_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa2.entity.Subject;
import uz.alif.lesson_jpa2.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    public String AddSubject(@RequestBody Subject subject){
        boolean exitsByName = subjectRepository.existsByName(subject.getName());
        if (exitsByName)
            return "this name already exits";
        subjectRepository.save(new Subject(subject.getName()));
        return "subject added";
    }

    @GetMapping
    public List<Subject> GetAllUniversity(){
        return subjectRepository.findAll();
    }


    @GetMapping("/{id}")
    public Subject GetOneId(@PathVariable int id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            return optionalSubject.get();
        }else {
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public String  DelById(@PathVariable ("id") int id){
        subjectRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("{id}")
    public String editById(@PathVariable int id, @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "edited subject";
        }
        return "subject not found";
    }
}
