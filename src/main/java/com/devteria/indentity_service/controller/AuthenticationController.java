package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.AuthenticationRequest;
import com.devteria.indentity_service.dto.request.IntrospectRequest;
import com.devteria.indentity_service.dto.response.AuthenticationResponse;
import com.devteria.indentity_service.dto.response.IntrospectResponse;
import com.devteria.indentity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // nếu kh khai báo sẽ dc lombok làm cho final

public class AuthenticationController {
        AuthenticationService authenticationService;

        @PostMapping("/token")
        // api login
             ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
                var result = authenticationService.authenticate(request);
                return ApiResponse.<AuthenticationResponse>builder()
                        .result(result)
            .build();
        }
     @PostMapping("/introspect")
        // api login
                 ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
                 throws ParseException, JOSEException {
                 var result = authenticationService.introspect(request);
             return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build(); // kiem tra token chu ky
    }
}
