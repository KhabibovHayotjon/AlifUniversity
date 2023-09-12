package uz.alif.lesson_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa2.entity.Faculity;
import uz.alif.lesson_jpa2.entity.Group;
import uz.alif.lesson_jpa2.payload.GroupDto;
import uz.alif.lesson_jpa2.repository.FacilityRepository;
import uz.alif.lesson_jpa2.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacilityRepository facilityRepository;

    @PostMapping
    public String add(@RequestBody GroupDto groupDto){
        boolean existsByNameAndFaculityId = groupRepository.existsByNameAndFaculityId(groupDto.getName(), groupDto.getFaculityId());
        if (existsByNameAndFaculityId)
            return "this faculity has this group";
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculity> faculityOptional = facilityRepository.findById(groupDto.getFaculityId());
        if (faculityOptional.isEmpty())
            return "this faculity is not found";
            group.setFaculity(faculityOptional.get());
            groupRepository.save(group);
            return "group saved";
    }

//    vazirlik
    @GetMapping
    public List<Group> getAllGroup(){
        return groupRepository.findAll();
    }


//    universtet
    @GetMapping("/hello/{id}")
    public List<Group> getAllGroupById(@PathVariable int id){
        return groupRepository.findAllByFaculity_UniversityId(id);
    }

    @GetMapping("/{id}")
    public Group GetOneId(@PathVariable int id){
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()){
            return groupOptional.get();
        }else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public String EditGroupById(@PathVariable int id, @RequestBody GroupDto groupDto){
        boolean exitsByName = groupRepository.existsByName(groupDto.getName());
        if (exitsByName){
            return "there is this group";
        }

        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()){
            Group group = groupOptional.get();
            group.setName(groupDto.getName());
            groupRepository.save(group);
            return "the group name has been changed";
        }else {
            return "not edited";
        }
    }

    @DeleteMapping("/{id}")
    public String DelById(@PathVariable int id){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            groupRepository.deleteById(id);
            return "deleted";
        }else {
            return "not deleted";
        }
    }
}
