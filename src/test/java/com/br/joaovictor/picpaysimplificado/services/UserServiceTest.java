package com.br.joaovictor.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.UserType;
import com.br.joaovictor.picpaysimplificado.repositories.UserRepostory;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

   @InjectMocks
   public UserService userService;
   @Mock
   public UserRepostory userRepostory;
   User userTest = new User();

   @BeforeEach
   void setup() {
      BigDecimal balance = new BigDecimal(1000);
      userTest.setId(1L);
      userTest.setBalance(balance);
      userTest.setUserType(UserType.COMMOM);
      userTest.setDocument("123456789");
      userTest.setEmail("emailGenerico@gmail.com");
      userTest.setFirstName("Usuario");
      userTest.setLastName("De Tests");
      userTest.setPassword("12345678");
   }

   // -------ValidateTransactionTests--------
   @Test
   void ValidateTransactionSuccessfully() {
      BigDecimal amout = new BigDecimal(500);
      assertDoesNotThrow(() -> userService.validatedTransaction(userTest, amout));
   }

   @Test
   void ValidateTransactionWhenBalanceIsInsufficient() {
      BigDecimal amout = new BigDecimal(1500);
      Exception exception = assertThrows(Exception.class,
            () -> userService.validatedTransaction(userTest, amout));
   }

   @Test
   void ValidateTransactionWhenUserIsMerchant() {
      BigDecimal amout = new BigDecimal(500);
      userTest.setUserType(UserType.MERCHANT);
      Exception exception = assertThrows(Exception.class,
            () -> userService.validatedTransaction(userTest, amout));
   }
   // -------findUserByIdTests--------

   @Test
   void findUserByIdSuccessfully() throws Exception {
      when(userRepostory.findUserById(1L))
         .thenReturn(Optional.of(userTest));

      User user = userService.findUserById(1L);
      assertEquals(1L, user.getId());
   }

   @Test
   void findUserByIdNotFound() {
      when(userRepostory.findUserById(1L))
         .thenReturn(Optional.empty());

      assertThrows(Exception.class,
         () -> userService.findUserById(1L));
   }

}
