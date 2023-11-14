package api.camp.controller;

import api.camp.model.CampsiteDTO;
import api.camp.model.Error;
import api.camp.service.CampSiteService;
import api.camp.service.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/campsites")
@CrossOrigin(origins = "http://localhost:3000")
public class CampSiteController {

  private CampSiteService campSiteService;
  private JwtTokenService jwtTokenService;
  private ObjectMapper mapper;


  @PostMapping()
  public ResponseEntity<String> createCamp(
      @RequestHeader("Authorization") String authorizationHeader,
      @Valid @RequestBody CampsiteDTO campsite)
      throws JsonProcessingException {
    log.info("createCamp controller");
    if (!jwtTokenService.isValidToken(authorizationHeader)) {
      return invalidToken();
    }
    return campSiteService.createCamp(campsite);
  }

  @GetMapping("/{campsiteid}")
  public ResponseEntity<String> getCamp(@RequestHeader("Authorization") String authorizationHeader,
      @PathVariable String campsiteid) throws JsonProcessingException {
    log.info("getCamp controller");
    if (!jwtTokenService.isValidToken(authorizationHeader)) {
      return invalidToken();
    }
    return null;
  }


  @PatchMapping("/{campsiteid}")
  public ResponseEntity<String> updateCamp(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String campsiteid,
      @RequestBody CampsiteDTO campsiteDTO) throws JsonProcessingException {
    log.info("updateCamp controller");
    if (!jwtTokenService.isValidToken(authorizationHeader)) {
      return invalidToken();
    }
    return null;
  }

  @DeleteMapping("/{campsiteid}")
  public ResponseEntity<String> deleteCamp(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String campsiteid)
      throws JsonProcessingException {
    log.info("deleteCamp controller");
    if (!jwtTokenService.isValidToken(authorizationHeader)) {
      return invalidToken();
    }
    return null;
  }

  private ResponseEntity<String> invalidToken() throws JsonProcessingException {
    Error error
        = Error.builder()
        .cause("JwtToken")
        .message("Invalid JwtToken")
        .build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(mapper.writeValueAsString(error));
  }

}
