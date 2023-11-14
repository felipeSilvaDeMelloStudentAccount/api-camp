package api.camp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Author {

  @NotBlank(message = "Username cannot be blank")
  private String username;
  @NotBlank(message = "UserId cannot be blank")
  @JsonIgnore
  private String userId;

}
