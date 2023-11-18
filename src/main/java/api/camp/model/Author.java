package api.camp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Author {

    private String username;
    private String userId;

}
