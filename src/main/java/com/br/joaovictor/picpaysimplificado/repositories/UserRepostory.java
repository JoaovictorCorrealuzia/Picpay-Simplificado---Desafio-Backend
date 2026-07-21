package com.br.joaovictor.picpaysimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;

public interface UserRepostory extends JpaRepository<User, Long>{
   Optional<User> findUserByDocument(String document);
   Optional<User> findUserById(Long id);
}
