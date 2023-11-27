package api.camp.repository;

import api.camp.collection.Campsite;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampsiteRepository extends MongoRepository<Campsite, String> {

  /**
   * Find by country or city or name list.
   *
   * @param country Country
   * @param city    City
   * @param name    Name
   * @return a list of campsites based on the search criteria
   */
  @Query("{'addressDetails.country': ?0, 'addressDetails.city': ?1, 'name': {$regex: ?2, $options: 'i'}}")
  List<Campsite> findByCountryOrCityOrName(String country, String city, String name);
}
