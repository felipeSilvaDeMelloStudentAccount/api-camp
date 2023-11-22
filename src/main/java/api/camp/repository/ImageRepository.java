package api.camp.repository;


import api.camp.collection.Image;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

  @Query("{'campsiteId': ?0}")
  List<Image> findByCampsiteId(String campsiteId);

}
