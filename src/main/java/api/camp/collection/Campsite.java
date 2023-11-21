package api.camp.collection;


import api.camp.model.Address;
import api.camp.model.Author;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "campsites")
@Getter
@Setter
@Builder
@JsonDeserialize(builder = Campsite.CampsiteBuilder.class)
@JsonSerialize(as = Campsite.class)
public class Campsite {

  @Id
  private String id;
  private String name;
  private List<String> imageIds;
  private String description;
  private int rating;
  @JsonSerialize
  @JsonDeserialize
  private LocalDateTime createdDate;
  @JsonSerialize
  @JsonDeserialize
  private Address addressDetails;
  @JsonSerialize
  @JsonDeserialize
  private Author author;
}
