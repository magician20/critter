package com.magician.critter.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.pet.data.PetRepository;
import com.magician.critter.user.data.entity.Customer;
import com.magician.critter.user.data.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class PetService {

    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(Pet pet) {
        pet = petRepository.save(pet);
        Customer customer = pet.getOwner();
        //Once the pet has been saved then add it to the customer entity
        List<Pet> pets = customer.getPets();
         if (pets != null)
            pets.add(pet);
        else {
            pets = new ArrayList<Pet>();
            pets.add(pet);
        }

        customer.setPets(pets);
        customerRepository.save(customer);

        return pet;
    }

    public Pet getPet(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new PetNotFoundException();
        }
        return pet.get();
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        // then get the list of pets that owned by the ownerId
        return petRepository.findAllByOwnerId(ownerId);
    }
}
