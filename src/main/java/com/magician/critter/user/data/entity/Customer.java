package com.magician.critter.user.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.magician.critter.pet.data.Pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer extends User {

    // phoneNumber changebale depend on customer so it will be a mutable naturalId
    // If natural ids values changed, it is possible for this mapping to become out
    // of date until a flush occurs.
    // Hibernate will attempt to discover any such pending changes and adjust them
    // when the load() or getReference() methods are executed
    // disable this checking by calling setSynchronizationEnabled(false) (the default is true).
    // @NaturalId(mutable = true, unique = true) //part of hibernate, cost more operation if mutable
    private String phoneNumber;

    private String notes;

    // What happen if customer delete the pet or his account & he have reigstered
    // schedule in that day
    // that mean delet must be handle carefully and implemented to check before delete
    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Pet> pets = new ArrayList<>();

    
    public void addPet(Pet pet){
        pet.setOwner(this);
        pets.add(pet);
    }

}
