package com.tic.vacupet.pets.infrastructure;

import com.tic.vacupet.pets.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByOwner_Id(String ownerId);
}
