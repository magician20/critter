package com.magician.critter.schedule.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.user.data.entity.Employee;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByEmployees(Employee employee);
    List<Schedule> findByPets(Pet pet);

    // @Query("select s from Schedule s join fetch s.pets p where p.id = :id")
    // List<Schedule> findAllByPetId(long id);

    // @Query("select s from Schedule s join fetch s.employees e where e.id = :id")
    // List<Schedule> findAllByEmployeeId(long id);

    @Query("select s from Schedule s join s.pets p join p.owner o where o.id = :id")
    List<Schedule> findByOwnerId(long id);

}
