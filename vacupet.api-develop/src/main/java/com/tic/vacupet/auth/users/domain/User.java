package com.tic.vacupet.auth.users.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Length(max = 20, message = "{user.username.length}")
    private String username;

    @Length(max = 255, message = "{user.password.length}")
    @NotNull(message = "{user.password.notnull}")
    private String password;

    @Length(max = 60, message = "{user.email.length}")
    @NotNull(message = "{user.email.notnull}")
    private String email;

    @GeneratedValue
    private Date createdAt;

    @GeneratedValue
    private Date updatedAt;

    @GeneratedValue
    private Date lastLogin;

    @GeneratedValue
    private Date lastPasswordChange;
}
