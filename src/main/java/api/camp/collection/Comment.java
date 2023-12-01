package api.camp.collection;

import api.camp.model.campsites.Author;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Getter
@Setter
@Builder
public class Comment {

  @Id
  private String id;
  private String campsiteId;
  private String text;
  private LocalDateTime createdDate;
  private Author author;
}
