package api.camp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GeoLocation {

  @NotBlank(message = "Latitude is mandatory")
  private int latitude;
  @NotBlank(message = "Longitude is mandatory")
  private int longitude;

}
