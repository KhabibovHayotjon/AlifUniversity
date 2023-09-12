package uz.alif.lesson_jpa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDto {
    private String name;
    private String region;
    private String district;
    private String street;

}
