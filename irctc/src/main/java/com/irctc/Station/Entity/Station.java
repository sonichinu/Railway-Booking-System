package com.irctc.Station.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.Bookings.Entity.Bookings;
import com.irctc.Route.Entity.Route;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany( mappedBy = "station",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties({"station"})
    private List<Route> route;

    @OneToMany(mappedBy = "tostation", cascade = CascadeType.ALL)
    private List<Bookings> booking;

    @OneToMany(mappedBy = "fromstation", cascade = CascadeType.ALL)
    private List<Bookings> booking2;
}
