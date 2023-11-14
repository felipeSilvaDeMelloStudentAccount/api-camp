package api.camp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonDeserialize(builder = api.camp.model.CampsiteDTO.CampsiteDTOBuilder.class)
@JsonSerialize(as = api.camp.model.CampsiteDTO.class)
public class CampsiteDTO {

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
  @NotBlank(message = "Address cannot be blank")
  private Address addressDetails;
  @NotBlank(message = "CreatedBy cannot be blank")
  private Author createdBy;
}
