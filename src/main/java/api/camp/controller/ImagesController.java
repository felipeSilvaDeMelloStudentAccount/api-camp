package api.camp.controller;

import static api.camp.service.JwtTokenService.validateToken;

import api.camp.collection.Image;
import api.camp.service.ImageService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(path = "/v1/campsites/{campsiteid}/images", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagesController {

  private ImageService imageService;

  @PostMapping()
  public ResponseEntity<Map<String, String>> uploadImage(
      @RequestHeader("Authorization") String authorizationHeader,
      @PathVariable String campsiteid, @RequestParam("file") MultipartFile file) {
    log.info("uploadImage controller");
    validateToken(authorizationHeader);
    Map<String, String> response = new HashMap<>();
    try {
      String fileId = imageService.storeImage(campsiteid, file);
      response.put("imageId", fileId);
      return ResponseEntity.ok(response);
    } catch (IOException e) {
      response.put("error", "Failed to upload image");
      response.put("message", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping
  public ResponseEntity<List<Image>> getImages(@PathVariable String campsiteid)
      throws IOException {
    log.info("getImages controller");
    List<Image> images = imageService.getImagesByCampsiteId(campsiteid);
    return ResponseEntity.ok(images);
  }

  @DeleteMapping("/{imageId}")
  public ResponseEntity<Map<String, String>> deleteImage(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String campsiteid,
      @PathVariable String imageId) {
    log.info("deleteImage controller");
    validateToken(authorizationHeader);
    imageService.deleteImage(imageId);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Image deleted successfully");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/all")
  public ResponseEntity<Map<String, String>> deleteImages(
      @RequestHeader("Authorization") String authorizationHeader) {
    log.info("deleteImage controller");
    validateToken(authorizationHeader);
    imageService.deleteAllImages();
    Map<String, String> response = new HashMap<>();
    response.put("message", "Image deleted successfully");
    return ResponseEntity.ok(response);
  }

}
