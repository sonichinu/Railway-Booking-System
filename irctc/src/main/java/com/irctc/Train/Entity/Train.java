package com.irctc.Train.Entity;

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
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int number;
    private String type;
    @OneToMany( mappedBy = "train",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties({"train","station"})
    private List<Route> route;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"train","user","fromstation","tostation"})
    private List<Bookings> booking;

}
