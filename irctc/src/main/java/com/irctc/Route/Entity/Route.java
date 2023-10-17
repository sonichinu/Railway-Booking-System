package com.irctc.Route.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.Station.Entity.Station;
import com.irctc.Train.Entity.Train;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.ALL})
    //   @JsonBackReference
    @JsonIgnoreProperties({"route","booking"})
    private Train train;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.ALL})
    //   @JsonBackReference
    @JsonIgnoreProperties({"route","booking","booking2"})
    private Station station;

    private Time arrivalTime;

    private Time departureTime;

    private int distance;
}
