package api.camp.service;

import api.camp.collection.Campsite;
import api.camp.model.campsites.CampsiteDTO;
import api.camp.model.campsites.CampsiteUpdateDTO;
import api.camp.repository.CampsiteRepository;
import java.net.URI;
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

  private static void updateGeoLocation(CampsiteUpdateDTO campsiteUpdateDTO, Campsite campsite) {
    log.debug("updateGeoLocation method");
    if (0 != campsiteUpdateDTO.getAddressDetails().getGeoLocation().getLatitude()) {
      campsite.getAddressDetails().getGeoLocation()
          .setLatitude(campsiteUpdateDTO.getAddressDetails().getGeoLocation().getLatitude());
    }
    if (0 != campsiteUpdateDTO.getAddressDetails().getGeoLocation().getLongitude()) {
      campsite.getAddressDetails().getGeoLocation()
          .setLongitude(
              campsiteUpdateDTO.getAddressDetails().getGeoLocation().getLongitude());
    }
  }

  private static void updateAddress(CampsiteUpdateDTO campsiteUpdateDTO, Campsite campsite) {
    log.debug("updateAddress method");
    if (null != campsiteUpdateDTO.getAddressDetails().getAddressLine1()) {
      campsite.getAddressDetails()
          .setAddressLine1(campsiteUpdateDTO.getAddressDetails().getAddressLine1());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getAddressLine2()) {
      campsite.getAddressDetails()
          .setAddressLine2(campsiteUpdateDTO.getAddressDetails().getAddressLine2());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getAddressLine3()) {
      campsite.getAddressDetails()
          .setAddressLine3(campsiteUpdateDTO.getAddressDetails().getAddressLine3());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getAddressLine4()) {
      campsite.getAddressDetails()
          .setAddressLine4(campsiteUpdateDTO.getAddressDetails().getAddressLine4());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getAddressLine5()) {
      campsite.getAddressDetails()
          .setAddressLine5(campsiteUpdateDTO.getAddressDetails().getAddressLine5());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getCity()) {
      campsite.getAddressDetails()
          .setCity(campsiteUpdateDTO.getAddressDetails().getCity());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getCountry()) {
      campsite.getAddressDetails()
          .setCountry(campsiteUpdateDTO.getAddressDetails().getCountry());
    }
    if (null != campsiteUpdateDTO.getAddressDetails().getEirCode()) {
      campsite.getAddressDetails()
          .setEirCode(campsiteUpdateDTO.getAddressDetails().getEirCode());
    }
  }

  public List<Campsite> getAllCampsites() {
    log.info("getAllCampsites service");
    Sort sort = Sort.by(Sort.Order.asc("createdDate"));
    return campsiteRepository.findAll(sort);
  }

  public Campsite getCampsite(String id) {
    log.info("getCampsite service");
    return campsiteRepository.findById(id).orElseThrow();
  }

  public ResponseEntity<String> deleteCampsite(String id) {
    log.info("deleteCampsite service");
    campsiteRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  //Create a new campsite and validate all fields and also check
  //if the campsite already exists also check if the campsite is within the boundaries of the location
  //return a 201 if the campsite is created successfully
  //return a 400 if the campsite already exists or if the campsite is not within the boundaries of the location
  public ResponseEntity<String> createCamp(CampsiteDTO campsiteDTO) {
    log.info("createCamp service");
    Campsite campsite = Campsite.builder()
        .name(campsiteDTO.getName())
        .description(campsiteDTO.getDescription())
        .rating(campsiteDTO.getRating())
        .addressDetails(campsiteDTO.getAddressDetails())
        .author(campsiteDTO.getAuthor())
        .build();
    log.info("campsite collection: {}", campsite.toString());
    campsiteRepository.save(campsite);
    log.info("campsite saved successfully");
    //create  uri for the newly created campsite and return to the client
    return ResponseEntity.created(URI.create("/v1/campsites/" + campsite.getId())).build();
  }

  public ResponseEntity<String> updateCampsite(String id, CampsiteUpdateDTO campsiteUpdateDTO) {
    log.info("updateCampsite service");
    Campsite campsite = campsiteRepository.findById(id).orElseThrow();
    //only set if the value is present in the request else keep the old value
    if (null != campsiteUpdateDTO.getName()) {
      campsite.setName(campsiteUpdateDTO.getName());
    }
    if (null != campsiteUpdateDTO.getDescription()) {
      campsite.setDescription(campsiteUpdateDTO.getDescription());
    }
    if (0 != campsiteUpdateDTO.getRating()) {
      campsite.setRating(campsiteUpdateDTO.getRating());
    }
    if (null != campsiteUpdateDTO.getAddressDetails()) {
      updateAddress(campsiteUpdateDTO, campsite);
      if (null != campsiteUpdateDTO.getAddressDetails().getGeoLocation()) {
        updateGeoLocation(campsiteUpdateDTO, campsite);
      }
      campsite.setAddressDetails(campsiteUpdateDTO.getAddressDetails());
    }
    campsiteRepository.save(campsite);

    log.info("campsite updated successfully");
    return ResponseEntity.ok().build();
  }

  public List<Campsite> searchCampsites(String country, String city, String name) {
    log.info("searchCampsites service");
    return campsiteRepository.findByCountryOrCityOrName(country, city, name);
  }
}
