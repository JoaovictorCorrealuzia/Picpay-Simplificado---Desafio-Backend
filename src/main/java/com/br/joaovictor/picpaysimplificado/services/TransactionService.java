package com.br.joaovictor.picpaysimplificado.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.joaovictor.picpaysimplificado.dtos.TransactionDTO;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Transaction.Transaction;
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
   @Autowired
   NotificationService notificationService;

   public Transaction createTransaction(TransactionDTO transactional) throws Exception {
      User sender = this.userService.findUserById(transactional.senderId());
      User receiver = this.userService.findUserById(transactional.receiverId());

      userService.validatedTransaction(sender, transactional.value());

      boolean isAuthorized = this.authorizeTransaction();
      if (!isAuthorized) {
         throw new Exception("Transação não autorizada");
      }

      Transaction newTransaction = new Transaction();
      newTransaction.setAmount(transactional.value());
      newTransaction.setReceiver(receiver);
      newTransaction.setSender(sender);
      newTransaction.setTimestamp(LocalDateTime.now());

      sender.setBalance(sender.getBalance().subtract(transactional.value()));
      receiver.setBalance(receiver.getBalance().add(transactional.value()));

      this.transactionalRepository.save(newTransaction);
      this.userService.saveUser(sender);
      this.userService.saveUser(receiver);
      
      //A exceção ja é tratada porem para o funcionamento da aplicação estão comentadas esses linhas
      //this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");
      //this.notificationService.sendNotification(sender, "Transação enviada com sucesso");

      return newTransaction;
   }

   public boolean authorizeTransaction() {
      try{
         ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
         
         Map dataResponse = (Map) authorizationResponse.getBody().get("data");
         return (boolean) dataResponse.get("authorization");

      }catch (HttpClientErrorException.Forbidden e){
         return false;
      }
   }
}
