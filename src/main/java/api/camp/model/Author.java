package api.camp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String username;
    @JsonIgnore
    private String userId;

}
