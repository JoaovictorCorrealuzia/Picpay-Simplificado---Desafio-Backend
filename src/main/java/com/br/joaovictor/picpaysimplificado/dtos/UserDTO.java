package com.br.joaovictor.picpaysimplificado.dtos;

import java.math.BigDecimal;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.UserType;

public record UserDTO(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {
}
