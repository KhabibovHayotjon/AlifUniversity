package uz.alif.lesson_jpa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacilityDto {
    private String name;
    private int UniversityId;
}
