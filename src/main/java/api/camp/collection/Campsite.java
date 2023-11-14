package api.camp.collection;


import api.camp.model.Address;
import api.camp.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
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
  @JsonIgnore
  private String id;
  @NotBlank(message = "Name cannot be blank")
  private String name;
  @NotBlank(message = "Images cannot be blank")
  private List<String> images;
  @NotBlank(message = "Description cannot be blank")
  private String description;
  @NotBlank(message = "Rating cannot be blank")
  @Min(value = 1, message = "Rating must be greater than 0")
  @Max(value = 5, message = "Rating must be less than 5")
  private Integer rating;
  private ZonedDateTime createdDate;
  @NotBlank(message = "Address cannot be blank")
  private Address addressDetails;
  @NotBlank(message = "Author cannot be blank")
  private Author createdBy;
}
