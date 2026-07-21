package com.br.joaovictor.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Transaction.Transaction;

public interface TransactionalRepository extends JpaRepository<Transaction, Long> {
}
