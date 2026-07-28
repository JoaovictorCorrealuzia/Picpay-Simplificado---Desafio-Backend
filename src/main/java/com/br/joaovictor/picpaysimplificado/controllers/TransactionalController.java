package com.br.joaovictor.picpaysimplificado.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.joaovictor.picpaysimplificado.dtos.TransactionDTO;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Transaction.Transaction;
import com.br.joaovictor.picpaysimplificado.services.TransactionService;

@Controller
@RestController
@RequestMapping("/transactions")
public class TransactionalController {

   @Autowired
   TransactionService transactionService;

   @PostMapping
   public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
      Transaction newTransaction = this.transactionService.createTransaction(transaction);
      return new ResponseEntity<>(newTransaction, HttpStatus.OK);
   } 
}
