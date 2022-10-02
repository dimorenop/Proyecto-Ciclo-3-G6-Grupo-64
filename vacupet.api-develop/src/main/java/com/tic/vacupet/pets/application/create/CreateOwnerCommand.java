package com.tic.vacupet.pets.application.create;

public record CreateOwnerCommand(String id, String idType, String name, String lastName, String email, String phone) {
}
