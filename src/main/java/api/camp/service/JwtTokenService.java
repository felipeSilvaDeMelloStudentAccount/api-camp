package api.camp.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtTokenService {

  public boolean isValidToken(String authorizationHeader) {
    log.debug("authorizationHeader : {}", authorizationHeader);
    //Remove Authorization prefix
    String jwtToken = authorizationHeader.substring(7);
    try {
      SignedJWT signedJWT = SignedJWT.parse(jwtToken);
      JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
      String userid = claims.getSubject();
      // Only allow the user to access their own data
      if (claims.getExpirationTime().before(new Date())) {
        log.debug("JWT token expired for user {}", userid);
        return false;
      }
    } catch (Exception e) {
      log.error("Error while validating JWT token error message : {}", e.getMessage());
      return false;
    }
    return false;
  }
}
