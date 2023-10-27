package com.irctc.train.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainInfoDto {
    private String trainName;
    private String fromStationName;
    private LocalDateTime fromStationArrivalTime;
    private String toStationName;
    private LocalDateTime toStationArrivalTime;
    private int fromStationDistance;
    private int toStationDistance;
}
