package api.camp.controller;

import api.camp.collection.Campsite;
import api.camp.model.CampsiteDTO;
import api.camp.service.CampSiteService;
import api.camp.service.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static api.camp.service.JwtTokenService.validateToken;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/campsites", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class CampSiteController {

    private CampSiteService campSiteService;
    private JwtTokenService jwtTokenService;
    private ObjectMapper mapper;


    @PostMapping()
    public ResponseEntity<String> createCamp(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody CampsiteDTO campsiteDTO) {
        log.info("createCamp controller");
        validateToken(authorizationHeader);
        return campSiteService.createCamp(campsiteDTO);
    }

    @GetMapping("/all")
    public List<Campsite> getAllCamps(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("getCamp controller");
        validateToken(authorizationHeader);
        return campSiteService.getAllCampsites();
    }

    @GetMapping("/{id}")
    public Campsite getCamp(@RequestHeader("Authorization") String authorizationHeader,
                            @PathVariable String id) throws JsonProcessingException {
        log.info("getCamp controller");
        validateToken(authorizationHeader);
        return campSiteService.getCampsite(id);
    }


    @PatchMapping("/{id}")
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
