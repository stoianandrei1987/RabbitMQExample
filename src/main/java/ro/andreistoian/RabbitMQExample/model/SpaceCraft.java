package ro.andreistoian.RabbitMQExample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "the name of the spacecraft")
    private String name;
    @JsonProperty("crew")
    @ApiModelProperty(notes = "crew number")
    private Integer crew;
    @JsonProperty("speed")
    @ApiModelProperty(notes = "spacecraft crew")
    private Integer speed;

}
