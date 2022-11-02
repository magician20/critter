package com.magician.critter.user.data.repository;

import org.springframework.stereotype.Repository;

import com.magician.critter.user.data.entity.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // //@Query("select c from Customer c where :pet member of c.pets")
    // Customer findDistinctByPets(Pet pet);

    @Query("select c from Customer c join c.pets p where p.id = :id")
    Optional<Customer> findByPetId(long id);

}
