package com.family.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Age must not be empty!")
    @Min(0)
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Parent parent;


    @OneToMany(
            mappedBy = "child",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Routine> routines = new HashSet<>();



    public void addRoutine(Routine routine) {
        routines.add(routine);
        routine.setChild(this);
    }


    public void removeRoutine(Routine routine) {
        routines.remove(routine);
        routine.setChild(null);
    }
}
