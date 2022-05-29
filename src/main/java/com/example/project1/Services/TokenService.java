package com.example.project1.Services;

import com.example.project1.CustomTemplate.Payload.response.JWTTokenSuccessResponse;
import com.example.project1.CustomTemplate.exceptions.ExceptionText;
import com.example.project1.Domain.Token;
import com.example.project1.Repository.TokenRepository;
import com.example.project1.Security.JWTTokenProvider;
import com.example.project1.Security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public static final Logger LOG = LoggerFactory.getLogger(TokenService.class);
    private final TokenRepository tokenRepository;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public TokenService(TokenRepository tokenRepository, JWTTokenProvider jwtTokenProvider) {
        this.tokenRepository = tokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JWTTokenSuccessResponse creat(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        Token token = new Token();
        token.setToken(jwt);
        token.setStatus(true);
        //tokenRepository.save(token);
        try {
            LOG.info("Save Token {} " );
            return new JWTTokenSuccessResponse(true, jwt);
        } catch (Exception ex) {
            LOG.error("Error during token creation", ex.getMessage());
            throw new ExceptionText("Token ");
        }

    }
}
