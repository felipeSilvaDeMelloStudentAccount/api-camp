package api.camp.collection;


import api.camp.model.Address;
import api.camp.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@JsonDeserialize(builder = Campsite.CampsiteBuilder.class)
@JsonSerialize(as = Campsite.class)
public class Campsite {

    @Id
    @JsonIgnore
    private String id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private List<String> images;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @Min(value = 1, message = "Rating must be greater than 0")
    @Max(value = 5, message = "Rating must be less than 5")
    private int rating;
    private LocalDateTime createdDate;
    private Address addressDetails;
    private Author author;
}
