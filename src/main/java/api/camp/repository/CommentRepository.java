package api.camp.repository;

import api.camp.collection.Comment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

  List<Comment> findByCampsiteId(String campsiteId);
}
