package api.camp.service;

import api.camp.collection.Campsite;
import api.camp.collection.Image;
import api.camp.repository.CampsiteRepository;
import api.camp.repository.ImageRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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


  private ImageRepository imageRepository;
  private CampsiteRepository campsiteRepository;


  private GridFsTemplate gridFsTemplate;

  public String storeImage(String campsiteId, MultipartFile file) throws IOException {
    // Store the image using GridFS
    ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
        file.getContentType());
    // Save metadata in the Image collection
    Image image = new Image();
    image.setCampsiteId(campsiteId);
    image.setFileId(fileId.toString());
    imageRepository.save(image);

    //Update Campsite with the new image ID
    Optional<Campsite> campsite = campsiteRepository.findById(campsiteId);
    if (campsite.isPresent()) {
      Campsite campsiteToUpdate = campsite.get();
      campsiteToUpdate.getImageIds().add(fileId.toString());
      campsiteRepository.save(campsiteToUpdate);
    }
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
}
