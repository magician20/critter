package com.magician.critter.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.magician.critter.pet.service.PetNotFoundException;
import com.magician.critter.user.data.entity.Customer;
import com.magician.critter.user.data.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Customer methods
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new UserNotFoundException();
        }

        return customerOpt.get();
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Customer getOwnerByPetId(long petId) {
        // get the customer object by petId
        Optional<Customer> owner = customerRepository.findByPetId(petId);
        if (!owner.isPresent()) {
            throw new PetNotFoundException();
        }
        return owner.get();
    }

}
