package com.magician.critter.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.pet.data.PetRepository;
import com.magician.critter.pet.service.PetNotFoundException;
import com.magician.critter.schedule.data.Schedule;
import com.magician.critter.schedule.data.ScheduleRepository;
import com.magician.critter.user.data.entity.Employee;
import com.magician.critter.user.data.repository.CustomerRepository;
import com.magician.critter.user.data.repository.EmployeeRepository;
import com.magician.critter.user.service.UserNotFoundException;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class ScheduleService {

    ScheduleRepository scheduleRepository;
    PetRepository petRepository;
    EmployeeRepository employeeRepository;
    CustomerRepository customerRepository;
    

    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository,
            EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new PetNotFoundException();
        }
        System.out.println(pet.get().toString());
        List<Schedule> findByPets = scheduleRepository.findByPets(pet.get());
        System.out.println(findByPets.toString());
        return findByPets;
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new UserNotFoundException();
        }
        System.out.println(employee.get().toString());
        List<Schedule> findByEmployees = scheduleRepository.findByEmployees(employee.get());
        System.out.println(findByEmployees.toString());
        return findByEmployees;
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        // get customer by id
        // Optional<Customer> customer = customerRepository.findById(customerId);
        // if (!customer.isPresent()) {
        // throw new UserNotFoundException();
        // }
        // // then get the List of pet associate to that customer
        // // then check if one ore more have schedule
        // List<Pet> pets = customer.get().getPets();// what if no list or empty
        // List<Schedule> schedules = new ArrayList<Schedule>();
        // if (pets != null || !pets.isEmpty()) {
        // for (Pet pet : pets) {
        // schedules.addAll(pet.getSchedules());
        // }
        // }

        // return schedules;
        return scheduleRepository.findByOwnerId(customerId);

    }

}
