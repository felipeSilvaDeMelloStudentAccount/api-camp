package api.camp.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class JwtTokenService {

    private JwtTokenService() {
        throw new IllegalStateException("JwtTokenService  Utility class");
    }

    public static void validateToken(String authorizationHeader) throws JwtException {
        log.debug("authorizationHeader : {}", authorizationHeader);
        try {
            //Remove Authorization prefix
            JWTClaimsSet claims = getJwtClaimsSet(authorizationHeader);
            String userId = claims.getSubject();
            // Only allow the user to access their own data
            if (claims.getExpirationTime().before(new Date())) {
                log.debug("JWT token expired for user {}", userId);
                throw new ExpiredJwtException(null, null,
                        "JWT token expired for user " + userId);
            }

        } catch (SignatureException e) {
            log.error("Error parsing JWT token. Signature validation failed. Error message: {}",
                    e.getMessage());
            throw new io.jsonwebtoken.SignatureException("Invalid JWT signature", e);
        } catch (JwtException | ParseException e) {
            log.error("Error parsing JWT token. Error message: {}", e.getMessage());
            throw new JwtException("Invalid JWT token", e);
        }
    }

    private static JWTClaimsSet getJwtClaimsSet(String authorizationHeader) throws ParseException {
        String jwtToken = extractToken(authorizationHeader);
        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        return signedJWT.getJWTClaimsSet();
    }

    private static String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    public static String getUserId(String authorizationHeader) throws ParseException {
        JWTClaimsSet claims = getJwtClaimsSet(authorizationHeader);
        return claims.getSubject();
    }
}
