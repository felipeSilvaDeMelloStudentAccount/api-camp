package api.camp.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Builder
@Setter
public class CampsiteDTO {

  @NotBlank(message = "Name cannot be blank")
  private String name;

  private String description;
  @Range(min = 1, max = 5, message = "Rating must be between 1 and 5")
  private int rating;
  @Valid
  private Address addressDetails;
  @Valid
  private Author author;
}
