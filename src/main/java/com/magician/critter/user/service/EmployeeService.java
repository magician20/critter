package com.magician.critter.user.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.magician.critter.user.data.entity.Employee;
import com.magician.critter.user.data.entity.EmployeeSkill;
import com.magician.critter.user.data.repository.EmployeeRepository;

@Service
public class EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Employee Methods
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee getEmployee(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new UserNotFoundException();
        }

        return employee.get();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.setWorkDays(daysAvailable);
        employeeRepository.save(employee);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        // //didn't work
        // String skillsString = Joiner.on(",").join(skills);
        // return employeeRepository.findByWorkDays(date.getDayOfWeek(), skillsString);

        // first Solution: if iam going to use JPQ i sholud get emplyees that avilable
        // on this day
        List<Employee> findByWorkDays = employeeRepository.findByWorkDays(date.getDayOfWeek());
        // then check every one of them if he have that set of skills
        return findByWorkDays.stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
        // second solution use native query with join.
    }

}
