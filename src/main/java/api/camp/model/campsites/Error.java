package api.camp.model.campsites;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@JsonSerialize
@JsonDeserialize
public class Error {

    private String cause;
    private String message;
}
