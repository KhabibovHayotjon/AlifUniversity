package uz.alif.lesson_jpa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private String name;
    private int faculityId;
}
