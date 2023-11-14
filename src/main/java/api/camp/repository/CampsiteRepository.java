package api.camp.repository;

import api.camp.collection.Campsite;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampsiteRepository extends MongoRepository<Campsite, String> {

  List<Campsite> findAllOrderByCreatedDate(Sort sort);


}
