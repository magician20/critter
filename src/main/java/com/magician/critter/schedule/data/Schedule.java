package com.magician.critter.schedule.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

    @ManyToMany(mappedBy = "schedules", targetEntity = Employee.class)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "schedules", targetEntity = Pet.class)
    private List<Pet> pets = new ArrayList<>();;

    // define date of work with specific pet
    private LocalDate date; 

    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "schedule_activities")
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities = new HashSet<>();

}