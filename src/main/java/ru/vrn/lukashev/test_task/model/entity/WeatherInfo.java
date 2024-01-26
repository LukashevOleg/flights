package ru.vrn.lukashev.test_task.model.entity;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class WeatherInfo {

    private int time;

    private int wind;

    private int visibility;

}
