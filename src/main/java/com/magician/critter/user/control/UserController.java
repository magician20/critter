package com.magician.critter.user.control;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.pet.service.PetService;
import com.magician.critter.user.data.entity.Customer;
import com.magician.critter.user.data.entity.Employee;
import com.magician.critter.user.service.CustomerService;
import com.magician.critter.user.service.EmployeeService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into
 * separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this
 * class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private CustomerService customerService;
    private EmployeeService employeeService;
    private PetService petService;

    public UserController(CustomerService customerService, EmployeeService employeeService, PetService petService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    // Customer Methods
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {

        return convertCustomerToCustomerDTO(customerService.saveCustomer(convertCustomerDTOToCustomer(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return convertCustomerToList(customerService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return convertCustomerToCustomerDTO(customerService.getOwnerByPetId(petId));
    }

    // Employee Methods
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEmployeeToEmployeeDTO(employeeService.saveEmployee(convertEmployeeDTOToEmployee(employeeDTO)));
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        return convertEmployeeToList(employeeService.getAllEmployees());
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return convertEmployeeToList(
                employeeService.findEmployeesForService(employeeRequestDTO.getSkills(), employeeRequestDTO.getDate()));
    }

    // Convert methods
    // Converts a CustomerDTO to a Customer Entity
    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        ArrayList<Pet> pets = new ArrayList<>();
        if (customerDTO.getPetIds() != null) {
            for (Long petId : customerDTO.getPetIds()) {
                Pet pet = petService.getPet(petId);
                pets.add(pet);
            }
        }
        customer.setPets(pets);
        return customer;
    }

    // Converts a Customer Entity to a CustomerDTO
    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        ArrayList<Long> petIds = new ArrayList<>();

        if (customer.getPets() != null) {
            for (Pet pet : customer.getPets()) {
                petIds.add(pet.getId());
            }
        }
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    // Converts a list of Customer Entities to a list of CustomerDTOs
    private List<CustomerDTO> convertCustomerToList(List<Customer> customers) {

        return customers.stream().map(c -> convertCustomerToCustomerDTO(c))
                .collect(Collectors.toList());
    }

    // Converts and EmployeeDTO to an Employee Entity
    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setWorkDays(employeeDTO.getDaysAvailable());
        employee.setSkills(employeeDTO.getSkills());

        return employee;
    }

    // Converts an Employee Entity to an EmployeeDTO
    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        employeeDTO.setDaysAvailable(employee.getWorkDays());
        employeeDTO.setSkills(employee.getSkills());
        return employeeDTO;
    }

    // Converts a list of Employee Entities to a list of EmployeeDTOs
    private List<EmployeeDTO> convertEmployeeToList(List<Employee> employees) {
        return employees.stream().map(e -> convertEmployeeToEmployeeDTO(e))
                .collect(Collectors.toList());
    }

}
