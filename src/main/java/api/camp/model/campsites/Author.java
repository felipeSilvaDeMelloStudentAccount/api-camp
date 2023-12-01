package api.camp.model.campsites;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Author {

  @NotBlank(message = "Username cannot be blank")
  private String userName;
  @NotBlank(message = "userId cannot be blank")
  private String userId;

}
