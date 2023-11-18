package api.camp.service;

import api.camp.collection.Campsite;
import api.camp.model.CampsiteDTO;
import api.camp.repository.CampsiteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;


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
        Campsite campsite = Campsite.builder()
                .name(campsiteDTO.getName())
                .images(campsiteDTO.getImages())
                .description(campsiteDTO.getDescription())
                .rating(campsiteDTO.getRating())
                .addressDetails(campsiteDTO.getAddressDetails())
                .author(campsiteDTO.getAuthor())
                .build();
        log.info("campsite collection: {}", campsite.toString());
        campsiteRepository.save(campsite);
        log.info("campsite saved successfully");
        return ResponseEntity.created(URI.create("/v1/campsites/" + campsite.getId())).build();
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
}
