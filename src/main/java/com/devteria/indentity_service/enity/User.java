package com.devteria.indentity_service.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY giúp ID tăng đúng thứ tự trong MySQL
    Long id;
    String username;
    String password;
    String firstName; // Sửa từ "firsName"
    String lastName;
    LocalDate dob;


}