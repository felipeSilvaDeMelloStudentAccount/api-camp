package api.camp.service;

import api.camp.collection.Image;
import api.camp.repository.ImageRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@AllArgsConstructor
@Service
public class ImageService {


  private final ImageRepository imageRepository;
  private final GridFsTemplate gridFsTemplate;

  public String storeImage(String campsiteId, MultipartFile file) throws IOException {
    // Store the image using GridFS
    ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
        file.getContentType());

    // Convert the image content to Base64
    String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

    // Save metadata in the Image collection
    Image image = Image.builder()
        .campsiteId(campsiteId)
        .fileId(fileId.toString())
        .base64Image(base64Image)
        .build();
    imageRepository.save(image);

    return fileId.toString();
  }

  public List<Image> getImagesByCampsiteId(String campsiteId) {
    // Retrieve images by campsite ID
    return imageRepository.findByCampsiteId(campsiteId);
  }

  public void deleteImage(String imageId) {
    // Delete the image from GridFS
    gridFsTemplate.delete(new Query(Criteria.where("_id").is(new ObjectId(imageId))));
    imageRepository.deleteById(imageId);
  }

  public void deleteAllImages() {
    imageRepository.deleteAll();
  }

}
