package ru.vrn.lukashev.test_task.service;


import org.springframework.stereotype.Service;
import ru.vrn.lukashev.test_task.model.dto.CheckFlightDto;
import ru.vrn.lukashev.test_task.model.entity.Flight;
import ru.vrn.lukashev.test_task.model.entity.WeatherInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class FlightService {


    public String checkFlights(CheckFlightDto checkFlightDto){
        Map<String, List<WeatherInfo>> weatherInfoMap = checkFlightDto.getForecast();

        checkFlightDto.getFlights()
                .forEach(flight -> {
                    if(flightIsRegularFilter(flight, weatherInfoMap))
                        flight.setCancelled(false);
                    else
                        flight.setCancelled(true);
                });

        return createReport(checkFlightDto.getFlights());
    }

    private String createReport(List<Flight> flights){
        StringBuilder report = new StringBuilder();

        flights.forEach(
                flight -> report.append(flight.toString()).append("\n")
        );

        return report.toString();
    }

    private boolean flightIsRegularFilter(Flight flight, Map<String, List<WeatherInfo>> weatherInfoMap){
        int timeDifference = getDurationTime(flight.getFrom(), flight.getTo());

        return (checkWind(weatherInfoMap.get(flight.getFrom()).get(flight.getDeparture()).getWind()) &&
                checkVisibility(weatherInfoMap.get(flight.getFrom()).get(flight.getDeparture()).getVisibility()) &&
                checkWind(weatherInfoMap.get(flight.getTo()).get(flight.getDeparture() + timeDifference + flight.getDuration()).getWind()) &&
                checkVisibility(weatherInfoMap.get(flight.getFrom()).get(flight.getDeparture() + timeDifference + flight.getDuration()).getVisibility()));
    }

    private boolean checkWind(int wind){
        return wind < 31;
    }

    private boolean checkVisibility(int visibility){
        return visibility > 200;
    }

    private int getDurationTime(String cityFrom, String cityTo){
        ZoneId zoneIdFrom = getZoneId(cityFrom);
        ZoneId zoneIdTo = getZoneId(cityTo);

        LocalDateTime fromTime = LocalDateTime.now(zoneIdFrom);
        LocalDateTime toTime = LocalDateTime.now(zoneIdTo);

        return (int) Duration.between(fromTime, toTime).toHours();
    }

    private ZoneId getZoneId(String city) {
        city = capitalizeFirstLetter(city);
        try {
            return ZoneId.of("Europe/" + city);
        } catch (Exception europeException) {
            try {
                return ZoneId.of("Asia/" + city);
            } catch (Exception asiaException) {
                throw new RuntimeException("Failed to get ZoneId for city: " + city, asiaException);
            }
        }
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

}
