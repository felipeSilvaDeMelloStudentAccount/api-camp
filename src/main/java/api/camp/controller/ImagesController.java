package api.camp.controller;

import static api.camp.service.JwtTokenService.validateToken;

import api.camp.collection.Image;
import api.camp.service.ImageService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/campsites", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagesController {

  private ImageService imageService;

  @PostMapping("/{campsiteId}/images")
  public ResponseEntity<String> uploadImage(
      @RequestHeader("Authorization") String authorizationHeader,
      @PathVariable String campsiteId, @RequestParam("file") MultipartFile file) {
    log.info("uploadImage controller");
    validateToken(authorizationHeader);
    try {
      String fileId = imageService.storeImage(campsiteId, file);
      return ResponseEntity.ok("Image uploaded with ID: " + fileId);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
    }
  }

  @GetMapping("/{campsiteId}/images")
  public ResponseEntity<List<Image>> getImages(@PathVariable String campsiteId) {
    log.info("getImages controller");
    List<Image> images = imageService.getImagesByCampsiteId(campsiteId);
    return ResponseEntity.ok(images);
  }

  @DeleteMapping("/images/{imageId}")
  public ResponseEntity<String> deleteImage(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String imageId) {
    log.info("deleteImage controller");
    validateToken(authorizationHeader);
    imageService.deleteImage(imageId);
    return ResponseEntity.ok("Image deleted successfully");
  }

}
