package api.camp.model.comments;

import api.camp.model.campsites.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@Setter
public class CommentDTO {

  @NotBlank(message = "Text cannot be blank")
  @Length(max = 500, message = "Text cannot be longer than 500 characters")
  private String text;
  private Author author;
}
