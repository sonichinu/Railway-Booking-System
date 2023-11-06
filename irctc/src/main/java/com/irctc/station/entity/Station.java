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
    @OneToMany( mappedBy = "station",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"station"})
    private List<Route> route;

    @OneToMany(mappedBy = "tostation", cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE})
    private List<Bookings> booking;

    @OneToMany(mappedBy = "fromstation", cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE})
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
