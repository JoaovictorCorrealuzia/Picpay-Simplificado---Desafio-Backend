package com.br.joaovictor.picpaysimplificado.services;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Transaction.Transaction;
import com.br.joaovictor.picpaysimplificado.repositories.TransactionalRepository;

import jakarta.annotation.Resource;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

   @InjectMocks
   public TransactionService transactionService;

   @Mock
   RestTemplate restTemplate;
   @Mock
   public TransactionalRepository transactionalRepository;
   @Mock
   public UserService userService;
   String urlExternalAPI = "https://util.devi.tools/api/v2/authorize";
   ResponseEntity<Map> response;

   //-------createTransactionTest-------
   @Test
   void createTransactionSuccesssfully(){

   }

   //-------authorizeTransactionTests-------
   
   public ResponseEntity<Map> createResponse(boolean authorization){
      Map<String,Boolean> data = new HashMap<>();
      data.put("authorization", authorization);
      Map<String,Map> responseMap = new HashMap<>();
      responseMap.put("data", data);

      return response = ResponseEntity.ok(responseMap);
   }

   @Test
   void authorizeTransactionSuccessfully(){
      createResponse(true);

      when(restTemplate.getForEntity(
            urlExternalAPI,
            Map.class))
            .thenReturn(response);

    boolean authorized = transactionService.authorizeTransaction();

    assertTrue(authorized);
   }

   @Test
   void authorizeTransactionNotAuthorized(){
      createResponse(false);
      
      when(restTemplate.getForEntity(
            urlExternalAPI,
            Map.class))
            .thenReturn(response);
      
      boolean authorized = transactionService.authorizeTransaction();
      assertFalse(authorized);
   }

   @Test
   void authorizeTransactionAPIdontWorking(){
      when(restTemplate.getForEntity(
            urlExternalAPI,
            Map.class)).thenThrow(new ResourceAccessException("Serviço fora do Ar"));

      assertThrows(ResourceAccessException.class, 
          () -> transactionService.authorizeTransaction());
   }

}
