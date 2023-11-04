package com.irctc.station.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.bookings.entity.Bookings;
import com.irctc.route.entity.Route;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnoreProperties({"user","fromstation","tostation"})
    private List<Bookings> booking2;

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
