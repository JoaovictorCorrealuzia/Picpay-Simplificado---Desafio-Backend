package com.br.joaovictor.picpaysimplificado.services;

import java.math.BigDecimal;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.joaovictor.picpaysimplificado.dtos.TransactionDTO;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;
import com.br.joaovictor.picpaysimplificado.repositories.TransactionalRepository;

@Service
public class TransactionService {

   @Autowired
   private UserService userService;
   @Autowired
   private TransactionalRepository transactionalRepository;
   @Autowired
   RestTemplate restTemplate;

   public void createTransaction(TransactionDTO transactional) throws Exception{
      User sender = this.userService.findUserById(transactional.senderId());
      User receiver = this.userService.findUserById(transactional.receiverId());
   
      userService.validatedTransaction(sender, transactional.value());
   }
}
