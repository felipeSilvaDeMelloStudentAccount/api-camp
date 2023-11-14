package api.camp.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import java.text.ParseException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtTokenService {

  public static void validateToken(String authorizationHeader) throws JwtException {
    log.debug("authorizationHeader : {}", authorizationHeader);
    //Remove Authorization prefix
    String jwtToken = authorizationHeader.substring(7);
    try {
      SignedJWT signedJWT = SignedJWT.parse(jwtToken);
      JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
      String userId = claims.getSubject();
      // Only allow the user to access their own data
      if (claims.getExpirationTime().before(new Date())) {
        log.debug("JWT token expired for user {}", userId);
        throw new ExpiredJwtException(null, null,
            "JWT token expired for user " + userId);
      }

      // Additional validation logic can be added here if needed.

    } catch (SignatureException e) {
      log.error("Error parsing JWT token. Signature validation failed. Error message: {}",
          e.getMessage());
      throw new io.jsonwebtoken.SignatureException("Invalid JWT signature", e);
    } catch (JwtException | ParseException e) {
      log.error("Error parsing JWT token. Error message: {}", e.getMessage());
      throw new JwtException("Invalid JWT token", e);
    }
  }
}
