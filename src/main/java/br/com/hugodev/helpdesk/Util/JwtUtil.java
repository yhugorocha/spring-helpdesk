package br.com.hugodev.helpdesk.Util;

import br.com.hugodev.helpdesk.exception.BusinessException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.public.key}")
    private RSAPrivateKey rsaPrivateKey;

    @Value("${jwt.private.key}")
    private RSAPublicKey rsaPublicKey;

    public String createToken(Authentication authentication){
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

            String username = authentication.getPrincipal().toString();
            String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

            return JWT.create()
                    .withIssuer("AUTH_HELP_DESK")
                    .withSubject(username)
                    .withClaim("authorities", authorities)
                    .withJWTId(UUID.randomUUID().toString())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 600000))
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new BusinessException("Unable to generate token");
        }
    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("AUTH_HELP_DESK")
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new BusinessException("Token invalid, not Authorized");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificationClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
