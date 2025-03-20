package com.devteria.indentity_service.dto.response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     Long id;
     String username;
     String password;
     String firstName; // Sửa từ "firsName"
     String lastName;
     LocalDate dob;
}
