package com.magician.critter.pet.control;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.magician.critter.pet.data.Pet;
import com.magician.critter.pet.service.PetService;
import com.magician.critter.user.data.entity.Customer;
import com.magician.critter.user.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertPetToPetDTO(petService.savePet(convertPetDTOToPet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return convertPetToList(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertPetToList(petService.getPetsByOwner(ownerId));
    }

    // Projection
    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setOwner(customer);
        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);

        Customer customer = pet.getOwner();
        petDTO.setOwnerId(customer.getId());
        return petDTO;
    }

    private List<PetDTO> convertPetToList(List<Pet> pets) {

        return pets.stream().map(p -> convertPetToPetDTO(p))
                .collect(Collectors.toList());
    }
}
