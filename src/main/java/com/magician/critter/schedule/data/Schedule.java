package com.magician.critter.schedule.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.user.data.entity.Employee;
import com.magician.critter.user.data.entity.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue
    // @Column(nullable = false)
    private Long id;

    @ManyToMany
    (
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
        //,fetch = FetchType.LAZY //defult
    )
    @JoinTable(
        name = "schedule_employee",
        joinColumns = { @JoinColumn(name = "schedule_id")},
        inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    (
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
       // ,fetch = FetchType.LAZY //defult
    )
    @JoinTable(
        name = "schedule_pet",
        joinColumns = { @JoinColumn(name = "schedule_id")},
        inverseJoinColumns = { @JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets = new ArrayList<>();;

    // define date of work with specific pet
    private LocalDate date; 

    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "schedule_activities")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities = new HashSet<>();

}