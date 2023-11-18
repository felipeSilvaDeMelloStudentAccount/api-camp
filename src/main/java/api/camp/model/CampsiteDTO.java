package api.camp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CampsiteDTO {

    private String name;
    private List<String> images;
    private String description;
    private int rating;
    private Address addressDetails;
    private Author author;
}
