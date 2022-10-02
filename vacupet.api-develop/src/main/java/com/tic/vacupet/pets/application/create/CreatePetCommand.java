package com.tic.vacupet.pets.application.create;

import io.jkratz.mediator.core.Command;

import java.util.Date;

public record CreatePetCommand(
        String breed,
        String name,
        Date birthDate,

        boolean hasChip,
        boolean hasMedicalRecord,
        boolean hasAllergies,

        String observations,

        CreateOwnerCommand owner
) implements Command {
}
