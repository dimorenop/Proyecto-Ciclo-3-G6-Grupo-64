package com.tic.vacupet.api.pets;

import com.tic.vacupet.pets.application.create.CreatePetCommand;
import io.jkratz.mediator.core.Mediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin
public class PetController {

    private final Mediator mediator;

    public PetController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody CreatePetCommand command) {
        mediator.dispatch(command);

        return ResponseEntity.ok(true);
    }
}
