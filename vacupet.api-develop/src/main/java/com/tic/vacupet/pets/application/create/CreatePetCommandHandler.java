package com.tic.vacupet.pets.application.create;

import com.tic.vacupet.pets.domain.Pet;
import com.tic.vacupet.pets.infrastructure.PetRepository;
import com.tic.vacupet.pets.owner.domain.Owner;
import io.jkratz.mediator.core.CommandHandler;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CreatePetCommandHandler implements CommandHandler<CreatePetCommand> {

    private final PetRepository repository;

    public CreatePetCommandHandler(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(@NonNull CreatePetCommand command) {
        val owner = Owner.builder()
                .id(command.owner().id())
                .idType(command.owner().idType())
                .name(command.owner().name())
                .lastName(command.owner().lastName())
                .email(command.owner().email())
                .phone(command.owner().phone())
                .build();

        val pet = Pet.builder()
                .breed(command.breed())
                .name(command.name())
                .birthDate(command.birthDate())
                .hasChip(command.hasChip())
                .hasMedicalRecord(command.hasMedicalRecord())
                .hasAllergies(command.hasAllergies())
                .observations(command.observations())
                .owner(owner)
                .build();

        repository.save(pet);
    }
}
