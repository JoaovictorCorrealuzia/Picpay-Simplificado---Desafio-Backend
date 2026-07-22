package com.br.joaovictor.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.joaovictor.picpaysimplificado.dtos.UserDTO;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.UserType;
import com.br.joaovictor.picpaysimplificado.repositories.UserRepostory;

@Service
public class UserService {
   @Autowired
   private UserRepostory userRepository;

   public void validatedTransaction(User sender,BigDecimal amout) throws Exception{
      if(sender.getUserType() == UserType.MERCHANT){
         throw new Exception("Usuario do Tipo Logista Nao esta autorizado a realizar uma transição");
      }
      if(sender.getBalance().compareTo(amout) < 0){
         throw new Exception("Saldo insuficiente");
      }
   }
   
   public User findUserById(Long id) throws Exception{
      return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuario Nao encontrado"));
   }

   public User createUser(UserDTO data){
      User newUser = new User(data);
      this.saveUser(newUser);
      return newUser;
   }

   public List<User> getAllUsers(){
      return this.userRepository.findAll();
   }

   public void saveUser(User user){
      this.userRepository.save(user);
   }
}
