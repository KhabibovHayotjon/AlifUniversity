package uz.alif.lesson_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa2.entity.Faculity;
import uz.alif.lesson_jpa2.entity.University;
import uz.alif.lesson_jpa2.payload.FacilityDto;
import uz.alif.lesson_jpa2.repository.FacilityRepository;
import uz.alif.lesson_jpa2.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/facility")
public class FaculityController {
    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    UniversityRepository universityRepository;

   @PostMapping()
   public String add(@RequestBody FacilityDto facilityDto){
       boolean existsByNameAndUniversityId = facilityRepository.existsByNameAndUniversityId(facilityDto.getName(), facilityDto.getUniversityId());
       if (existsByNameAndUniversityId)
           return "this university has this facility";
       Faculity faculity = new Faculity();
       faculity.setName(facilityDto.getName());
      Optional<University> optionalUniversity = universityRepository.findById(facilityDto.getUniversityId());
      if (optionalUniversity.isEmpty())
          return "this University is not found";
      faculity.setUniversity(optionalUniversity.get());
      facilityRepository.save(faculity);
      return "Faculity saved";
   }

    @PutMapping("/{id}")
    public String EditFacility(@RequestBody FacilityDto facilityDto, @PathVariable int id){
       boolean exitsByName = facilityRepository.existsByName(facilityDto.getName());

       if (exitsByName){
           return "this has facility";
       }
       Optional<Faculity> faculityOptional = facilityRepository.findById(id);
       if (faculityOptional.isPresent()){
           Faculity faculity = faculityOptional.get();
           faculity.setName(facilityDto.getName());
           facilityRepository.save(faculity);
           return "edited";
       }else {
           return "not editded";
       }

    }
//vazirlik uchun
@GetMapping
public List<Faculity> GetAll(){
       return facilityRepository.findAll();
   }

//universtet moderator uchun getByUniversityId
    @GetMapping("/{id}")
    public List<Faculity> GetAllByIdUn(@PathVariable  int id){
        return facilityRepository.hamasiniolishnativeQuery(id);
}


   @GetMapping("hello/{id}")
    public Faculity  GetOne(@PathVariable int id){
       Optional<Faculity> faculityOptional = facilityRepository.findById(id);
       if (faculityOptional.isPresent()){
           return faculityOptional.get();
       }else {
           return null;
       }
   }

   @DeleteMapping("/{id}")
    public String DelById(@PathVariable ("id") int id){
       Optional<Faculity> faculityOptional = facilityRepository.findById(id);
       if (faculityOptional.isPresent()) {
           facilityRepository.deleteById(id);
           return "deleted";
       }else {
           return "not found";
       }
   }












    //    @RequestMapping(value = "/DelOneFacility/{id}", method = RequestMethod.DELETE)
//    public String  DelOneFacility(@PathVariable int id){
//        faculityRepository.deleteById(id);
//        return "deleted";
//    }



//    @RequestMapping(value = "/GetAllFaciliyt",method = RequestMethod.GET)
//    public List<Faculity> GetAllFacility(){
//        return faculityRepository.findAll();
//    }
//

//
//    @RequestMapping(value = "/DelOneFacility/{id}", method = RequestMethod.DELETE)
//    public String  DelOneFacility(@PathVariable int id){
//        faculityRepository.deleteById(id);
//        return "deleted";
//    }

}
