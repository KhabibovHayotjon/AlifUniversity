package uz.alif.lesson_jpa2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname = "khabibov";
    @OneToOne(optional = false)
    private Address address;
    @ManyToOne
    private Group group;

    @ManyToMany
    private List<Subject> subjects;

}
