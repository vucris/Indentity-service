package com.devteria.indentity_service.service.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtService {
    private final String signerKey;

    public JwtService(@Value("${jwt.signerKey:default-secret-key}") String signerKey) {
        this.signerKey = signerKey;
    }
}
