package api.camp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @NotBlank(message = "Address Line 1 is mandatory")
  private String addressLine1;
  @NotBlank(message = "Address Line 2 is mandatory")
  private String addressLine2;
  private String addressLine3;
  private String addressLine4;
  private String addressLine5;
  @NotBlank(message = "Country is mandatory")
  private String country;
  private String eirCode;
  @NotBlank(message = "GeoLocation is mandatory")
  private GeoLocation geoLocation;

}
