package com.tic.vacupet.pets.owner.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Owner {

    @Id
    @Length(max = 10, message = "{owner.id.length}")
    private String id;

    @Length(max = 15, message = "{owner.id_type.length}")
    private String idType;

    @Length(max = 50, message = "{owner.name.length}")
    private String name;

    @Length(max = 50, message = "{owner.last_name.length}")
    private String lastName;

    @Length(max = 100, message = "{owner.email.length}")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{owner.email.pattern}")
    private String email;

    @Length(max = 10, message = "{owner.phone.length}")
    @Pattern(regexp = "^[0-9]*$", message = "{owner.phone.pattern}")
    private String phone;
}
