package com.magician.critter.user.data.entity;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.magician.critter.schedule.data.Schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee extends User {

    /// problem here that this is not only one value but set of values
    // below code for convert DayOfWeek to String
    // @Column(name = "days_avilable")
    // @Convert(converter = DayOfWeekToIntegerConverter.class)
    // but if DayOfWeek is an enum why not use below
    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.LAZY)
    @CollectionTable(
               name = "employee_work_days",//Table Name
               joinColumns = @JoinColumn(name = "id")//FK Column
        )
    @Column(name="work_days")
   // @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> workDays;

    // use default table name=(Employee_EmployeeSkill) & defult join coulmn
    // ex:@CollectionTable(name = "TBL_NAME_ENUM",joinColumns = @JoinColumn(name =
    // "TBL_ID")@Column(name = "ENUM_ID"))
    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.EAGER)
    @CollectionTable( name = "employee_skills", //Table Name
                      joinColumns = @JoinColumn(name = "id")//FK Column
                     )//,uniqueConstraints = @UniqueConstraint(columnNames = { "id", "skills" }))
    @Column(name="skills")                 
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ManyToMany(
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name = "employee_schedule",
        joinColumns = { @JoinColumn(name = "employee_id")},
        inverseJoinColumns = { @JoinColumn(name = "schedule_id")}
    )
    private List<Schedule> schedules;

}
