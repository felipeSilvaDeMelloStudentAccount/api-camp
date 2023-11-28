package api.camp.model.campsites;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address {

  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private String addressLine4;
  private String addressLine5;
  @NotBlank(message = "City cannot be blank")
  private String city;
  @NotBlank(message = "Country cannot be blank")
  private String country;
  private String eirCode;
  private GeoLocation geoLocation;

}
