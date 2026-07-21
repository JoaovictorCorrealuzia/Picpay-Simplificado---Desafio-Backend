package br.com.joaovictor.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaovictor.desafiopicpay.infrastructure.entity.Transaction.Transaction;

public interface TransactionalRepository extends JpaRepository<Transaction, Long> {
}
