package api.camp.controller;

import static api.camp.service.JwtTokenService.validateToken;

import api.camp.collection.Campsite;
import api.camp.model.CampsiteDTO;
import api.camp.service.CampSiteService;
import api.camp.service.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
      @Valid @RequestBody CampsiteDTO campsite) {
    log.info("createCamp controller");
    validateToken(authorizationHeader);
    return campSiteService.createCamp(campsite);
  }

  @GetMapping
  public List<Campsite> getAllCamps(@RequestHeader("Authorization") String authorizationHeader) {
    log.info("getCamp controller");
    validateToken(authorizationHeader);
    return campSiteService.getAllCampsites();
  }

  @GetMapping("/{campsite}")
  public ResponseEntity<String> getCamp(@RequestHeader("Authorization") String authorizationHeader,
      @PathVariable String campsite) throws JsonProcessingException {
    log.info("getCamp controller");
    validateToken(authorizationHeader);
    return null;
  }


  @PatchMapping("/{campsite}")
  public ResponseEntity<String> updateCamp(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String campsite,
      @RequestBody CampsiteDTO campsiteDTO) throws JsonProcessingException {
    log.info("updateCamp controller");
    validateToken(authorizationHeader);
    return null;
  }

  @DeleteMapping("/{campsite}")
  public ResponseEntity<String> deleteCamp(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String campsite)
      throws JsonProcessingException {
    log.info("deleteCamp controller");
    validateToken(authorizationHeader);

    return null;
  }

}
