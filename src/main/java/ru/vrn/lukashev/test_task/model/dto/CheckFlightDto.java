package ru.vrn.lukashev.test_task.model.dto;

import lombok.*;
import ru.vrn.lukashev.test_task.model.entity.Flight;
import ru.vrn.lukashev.test_task.model.entity.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class CheckFlightDto {

    Map<String, List<WeatherInfo>> forecast;

    ArrayList<Flight> flights;

}
