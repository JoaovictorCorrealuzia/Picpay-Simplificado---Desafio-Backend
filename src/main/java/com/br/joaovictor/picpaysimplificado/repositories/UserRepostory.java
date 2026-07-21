package br.com.joaovictor.desafiopicpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaovictor.desafiopicpay.infrastructure.entity.Users.User;

public interface UserRepostory extends JpaRepository<User, Long>{
   Optional<User> findUserByDocument(String document);
   Optional<User> findUserById(Long id);
}
