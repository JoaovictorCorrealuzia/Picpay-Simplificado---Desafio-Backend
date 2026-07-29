package com.br.joaovictor.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import com.br.joaovictor.picpaysimplificado.dtos.UserDTO;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.User;
import com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users.UserType;
import com.br.joaovictor.picpaysimplificado.repositories.UserRepostory;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

   @InjectMocks
   public UserService userService;
   @Mock
   public UserRepostory userRepostory;
   UserDTO userDTO;

   @BeforeEach
   void setup() {
      BigDecimal balance = new BigDecimal(1000);
      // ---------userDTO--------
      userDTO = new UserDTO(
         "Usuario",
         "De Tests",
         "123456789",
         "emailGenerico@gmail.com",
         "12345678",
         balance,
         UserType.COMMOM);
   }

   // -------ValidateTransactionTests--------
   @Test
   void ValidateTransactionSuccessfully() {
      User userTest = new User(userDTO);

      BigDecimal amout = new BigDecimal(500);
      assertDoesNotThrow(() -> userService.validatedTransaction(userTest, amout));
   }

   @Test
   void ValidateTransactionWhenBalanceIsInsufficient() {
      User userTest = new User(userDTO);

      BigDecimal amout = new BigDecimal(1500);
      Exception exception = assertThrows(Exception.class,
            () -> userService.validatedTransaction(userTest, amout));
   }

   @Test
   void ValidateTransactionWhenUserIsMerchant() {
      User userTest = new User(userDTO);

      BigDecimal amout = new BigDecimal(500);
      userTest.setUserType(UserType.MERCHANT);
      Exception exception = assertThrows(Exception.class,
            () -> userService.validatedTransaction(userTest, amout));
   }
   // -------findUserByIdTests--------

   @Test
   void findUserByIdSuccessfully() throws Exception {
      User userTest = new User(userDTO);
      userTest.setId(1L);
      
      when(userRepostory.findUserById(1L))
            .thenReturn(Optional.of(userTest));

      User user = userService.findUserById(1L);
      assertEquals(1L, user.getId());

      verify(userRepostory).findUserById(1L);
   }

   @Test
   void findUserByIdNotFound() {
      when(userRepostory.findUserById(1L))
            .thenReturn(Optional.empty());

      assertThrows(Exception.class,
            () -> userService.findUserById(1L));
      
      verify(userRepostory).findUserById(1L);

   }
   // -------createUserTests--------
   @Test
   void createUserSuccessfully() {
      User createdUser = userService.createUser(userDTO);
      
      assertNotNull(createdUser);
      assertEquals(userDTO.balance(), createdUser.getBalance());
      assertEquals(userDTO.document(), createdUser.getDocument());
      assertEquals(userDTO.email(), createdUser.getEmail());
      assertEquals(userDTO.firstName(), createdUser.getFirstName());
      assertEquals(userDTO.lastName(), createdUser.getLastName());
      assertEquals(userDTO.password(), createdUser.getPassword());
      assertEquals(userDTO.userType(), createdUser.getUserType());

      verify(userRepostory).save(any(User.class));
   }

}
