package api.camp.collection;


import api.camp.model.campsites.Address;
import api.camp.model.campsites.Author;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "campsites")
@Getter
@Setter
@Builder
public class Campsite {

    @Id
    private String id;
    @NotNull
    private String name;
    private List<String> imageIds;
    @NotNull
    private String description;
    @NotNull
    private int rating;
    private LocalDateTime createdDate;
    @Valid
    private Address addressDetails;
    @Valid
    private Author author;
}
