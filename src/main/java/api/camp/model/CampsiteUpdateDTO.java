package api.camp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class CampsiteUpdateDTO {

  private String name;
  private String description;
  private int rating;
  private Address addressDetails;
}
