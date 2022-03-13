package com.example.fightbook.controller;

import com.example.fightbook.service.Flight;
import com.example.fightbook.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {


    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("")
    public List<Flight> getEducationByIdRestful(@RequestParam String takeOffDate, @RequestParam String startLocation,
                                                @RequestParam String destination) {
        return flightService.getFlights(takeOffDate,startLocation,destination);
    }
}
