package com.example.fightbook.service;


import com.example.fightbook.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final String successMessage = "查询成功";

    private final String noFlightMessage = "对不起，当前航段无航班";

    public List<Flight> getFlights(String time, String startLocation, String destination) {
        if (destination.equals("伦敦")){
            throw new BusinessException("不支持国外航班", HttpStatus.BAD_REQUEST);
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate takeOffDate = LocalDate.parse(time, fmt);
        LocalDateTime takeoffTime = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime takeoffTime2 = LocalDateTime.of(2022, 3, 10, 10, 30);


        Flight cs2311 = Flight.builder().flightId("CS2311").time(takeoffTime).startLocation("成都").destination("深圳").build();
        Flight cs2463 = Flight.builder().flightId("CS2463").time(takeoffTime2).startLocation("成都").destination("深圳").build();
        List<Flight> flights = Arrays.asList(cs2311, cs2463);
        return flights.stream().filter(item -> isSupportedFlight(takeOffDate, startLocation, destination, item)).collect(Collectors.toList());
    }

    private boolean isSupportedFlight(LocalDate takeOffDate, String startLocation, String destination, Flight item) {
        return item.getStartLocation().equals(startLocation) && item.getDestination().equals(destination)
                && item.getTime().getDayOfYear() == takeOffDate.getDayOfYear() && item.getTime().getYear() == takeOffDate.getYear();
    }


}
