package api.camp.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampsiteDTO {

  private String name;
  private String description;
  private int rating;
  private Address addressDetails;
  private Author author;
}
