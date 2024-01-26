package ru.vrn.lukashev.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vrn.lukashev.test_task.model.dto.CheckFlightDto;
import ru.vrn.lukashev.test_task.service.FlightService;


@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/checkFlights", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkFlights(@RequestBody CheckFlightDto checkFlightDto){
        return flightService.checkFlights(checkFlightDto);
    }

}
