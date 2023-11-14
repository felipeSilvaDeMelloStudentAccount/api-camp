package api.camp.service;

import api.camp.collection.Campsite;
import api.camp.model.CampsiteDTO;
import api.camp.repository.CampsiteRepository;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class CampSiteService {

  private CampsiteRepository campsiteRepository;

  //Create a new campsite and validate all fields and also check
  //if the campsite already exists also check if the campsite is within the boundaries of the location
  //return a 201 if the campsite is created successfully
  //return a 400 if the campsite already exists or if the campsite is not within the boundaries of the location
  public ResponseEntity<String> createCamp(CampsiteDTO campsiteDTO) {
    log.info("createCamp service");

    //map campsiteDTO to campsite
    Campsite campsite = Campsite.builder()
        .name(campsiteDTO.getName())
        .images(campsiteDTO.getImages())
        .description(campsiteDTO.getDescription())
        .rating(campsiteDTO.getRating())
        .createdDate(ZonedDateTime.now())
        .addressDetails(campsiteDTO.getAddressDetails())
        .createdBy(campsiteDTO.getCreatedBy())
        .build();

    campsiteRepository.save(campsite);
    return ResponseEntity.ok("Campsite created successfully");
  }

  public ResponseEntity<List<Campsite>> getAllCampsites() {
    log.info("getAllCampsites service");
    Sort sort = Sort.by(Sort.Order.asc("createdDate"));
    List<Campsite> campsites = campsiteRepository.findAllOrderByCreatedDate(sort);
    return ResponseEntity.ok(campsites);
  }


}
