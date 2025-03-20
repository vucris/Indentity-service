package com.devteria.indentity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@Data
@Builder //tao object nhanh hon
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "username phai tren 3 ki tu")
    String username;
    @Size(min = 8, message = "password phai tren 8 ki tu")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

}