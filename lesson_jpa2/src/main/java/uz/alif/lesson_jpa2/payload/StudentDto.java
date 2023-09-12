package uz.alif.lesson_jpa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.lesson_jpa2.entity.Subject;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String name;
    private String lastname;
    private int[] subject_id;
    private int group_id;
    private String region;
    private String district;
    private String street;

}
