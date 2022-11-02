package com.magician.critter.pet.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.magician.critter.schedule.data.Schedule;
import com.magician.critter.user.data.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Customer owner;

    @ManyToMany(
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name = "pet_schedule",
        joinColumns = { @JoinColumn(name = "pet_id")},
        inverseJoinColumns = { @JoinColumn(name = "schedule_id")}
    )
    private List<Schedule> schedules = new ArrayList<>();
  
}