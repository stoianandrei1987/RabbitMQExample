package ro.andreistoian.RabbitMQExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "This is a cool spacecraft")
public class SpaceCraft {


    @JsonProperty("name")
    private String name;
    @JsonProperty("crew")
    private Integer crew;
    @JsonProperty("speed")
    private Integer speed;

}
