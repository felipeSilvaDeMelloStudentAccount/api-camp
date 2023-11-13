package api.camp.model.collection;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
  private List<String> images;
  private String description;
  private String address;
  private String rating;
}
