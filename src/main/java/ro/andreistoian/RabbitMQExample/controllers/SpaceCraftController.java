package ro.andreistoian.RabbitMQExample.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ro.andreistoian.RabbitMQExample.model.SpaceCraft;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "controller for adding and viewing spacecraft")
public class SpaceCraftController {

    List<SpaceCraft> spaceCrafts = new ArrayList<>();


    @GetMapping(path = "/spacecrafts", produces = "application/json")
    @ApiOperation(value = "this gets the entire list of spacecraft", response = List.class)
    public List<SpaceCraft> crafts() {
        return spaceCrafts;
    }


    @PostMapping(path = "/spacecrafts", consumes = "application/json")
    @ApiOperation(value = "this allows to concatenate existing list with new list of spacecraft", response = List.class)
    public List<SpaceCraft> createList(@ApiParam(value = "new list to add to old") @RequestBody List<SpaceCraft> crafts) {
        spaceCrafts.addAll(crafts);
        return spaceCrafts;
    }

    @GetMapping(path = "/spacecraftsfilter", produces = "application/json")
    @ApiOperation(value = "this lists spacecraft with speed between lower and upper limit", response = List.class)
    public List<SpaceCraft> craftsSpeedHigherThan(
            @ApiParam(value = "lower speed limit")
            @RequestParam(value = "lowerlimit", defaultValue = "0") Integer lower,
            @ApiParam(value = "higher speed limit")
            @RequestParam(value = "higherlimit",
                    defaultValue = Integer.MAX_VALUE + "")
                    Integer higher) {


        return spaceCrafts.stream().filter(s -> s.getSpeed() > lower && s.getSpeed() < higher).
                collect(Collectors.toList());
    }

}
