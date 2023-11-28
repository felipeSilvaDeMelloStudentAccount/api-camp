package api.camp.model.campsites;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeoLocation {

  private int latitude;
  private int longitude;

}
