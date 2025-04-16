package com.devteria.indentity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@Data
@Builder //tao object nhanh hon
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated; // nếu true là đúng tk mk
}
