package com.finanlice.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Date;
import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class JwtService {

    private final SecretKey signinKey;

    public JwtService(@Value("${jwt.secret}") String jwtSecret){

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        this.signinKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(UserDetails usuario){

        Date agora = new Date();

        Date expiracao = new Date(
                agora.getTime() + 1000L * 60 * 60
        );

        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(signinKey)
                .compact();

    }

    public String extrairEmail(String token){

    return Jwts.parser()
            .verifyWith(signinKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

}
