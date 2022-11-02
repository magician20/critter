package com.magician.critter.user.data.repository;

import org.springframework.stereotype.Repository;

import com.magician.critter.user.data.entity.Employee;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // IDK if this only will be solved with Native SQL or not (maybe join fetch is
    // solution ? not work)
    // @Query("select e from Employee e where :workDay member of e.workDays join
    // fetch e.skills s where s in (:skillsString)")

    // List<Employee> findByWorkDaysAndSkillsIn(DayOfWeek dayAvailable,
    // Set<EmployeeSkill> skills);

    @Query("select e from Employee e where :workDay member of e.workDays")
    List<Employee> findByWorkDays(DayOfWeek workDay);

}
