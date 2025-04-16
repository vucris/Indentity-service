package com.devteria.indentity_service.dto.response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserResponse {
     Long id;
     String username;
     String firstName; // Sửa từ "firsName"
     String lastName;
     LocalDate dob;
     Set<String> roles;
}
