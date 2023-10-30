package com.irctc.train.entity;

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
    @JsonIgnoreProperties({"train"})
    private List<Route> route;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"train","user","fromstation","tostation"})
    private List<Bookings> booking;

//    @OneToOne(mappedBy = "train", cascade = CascadeType.ALL)
//    private Seats seats;


    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", route=" + route +
                '}';
    }
}
