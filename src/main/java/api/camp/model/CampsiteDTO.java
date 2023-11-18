package api.camp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(builder = api.camp.model.CampsiteDTO.CampsiteDTOBuilder.class)
@JsonSerialize(as = api.camp.model.CampsiteDTO.class)
public class CampsiteDTO {

    private String name;
    private List<String> images;
    private String description;
    private int rating;
    private Address addressDetails;
    private Author author;
}
