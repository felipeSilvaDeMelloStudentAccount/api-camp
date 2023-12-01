package api.camp.repository;

import api.camp.collection.Campsite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampsiteRepository extends MongoRepository<Campsite, String> {

}
