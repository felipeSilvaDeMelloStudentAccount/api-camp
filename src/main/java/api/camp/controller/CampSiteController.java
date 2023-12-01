package api.camp.controller;

import api.camp.collection.Campsite;
import api.camp.model.campsites.CampsiteDTO;
import api.camp.model.campsites.CampsiteUpdateDTO;
import api.camp.service.CampSiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static api.camp.service.JwtTokenService.getUserId;
import static api.camp.service.JwtTokenService.validateToken;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/campsites", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class CampSiteController {

    private CampSiteService campSiteService;


    @PostMapping()
    public ResponseEntity<String> createCampsite(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody CampsiteDTO campsiteDTO) throws ParseException {
        log.info("createCamp controller");
        validateToken(authorizationHeader);
        String userId = getUserId(authorizationHeader);
        return campSiteService.createCamp(userId, campsiteDTO);
    }

    @GetMapping("/all")
    public List<Campsite> getAllCampsites() {
        log.info("getAllCampsites controller");
        return campSiteService.getAllCampsites();
    }

    @GetMapping("/{id}")
    public Campsite getCampsite(@PathVariable String id) {
        log.info("getCamp controller");
        return campSiteService.getCampsite(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCampsite(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String id,
            @RequestBody CampsiteUpdateDTO campsiteUpdateDTO) {
        log.info("updateCamp controller");
        validateToken(authorizationHeader);
        return campSiteService.updateCampsite(id, campsiteUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCamp(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
        log.info("deleteCamp controller");
        validateToken(authorizationHeader);
        return campSiteService.deleteCampsite(id);
    }
}
