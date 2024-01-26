package ru.vrn.lukashev.test_task.model.entity;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class Flight {

    private String no;

    private int departure;

    private String from;

    private String to;

    private int duration;

    private boolean cancelled;


    @Override
    public String toString() {
        return no + " | " +
                from + " -> " +
                to + " | " +
                (cancelled ? "отменен" : "по расписанию");
    }
}