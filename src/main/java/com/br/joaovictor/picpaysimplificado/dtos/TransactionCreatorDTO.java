package com.br.joaovictor.picpaysimplificado.dtos;

import java.math.BigDecimal;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;

public record TransactionCreatorDTO(BigDecimal amout, User receiver, User sender) {

}
