package com.br.joaovictor.picpaysimplificado.infrastructure.entity.Users;

import java.math.BigDecimal;

import com.br.joaovictor.picpaysimplificado.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String firstName;
   private String lastName;

   @Column(unique = true)
   private String document;

   @Column(unique = true)
   private String email;
   private String password;

   private BigDecimal balance;

   @Enumerated(EnumType.STRING)
   private UserType userType;

   public User(UserDTO data){
      this.balance = data.balance();
      this.document = data.document();
      this.email = data.email();
      this.firstName = data.firstName();
      this.lastName = data.lastName();
      this.password = data.password();
      this.userType = data.userType();
   }
}