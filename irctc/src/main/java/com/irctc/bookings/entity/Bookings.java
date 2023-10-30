package com.irctc.bookings.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.passengers.entity.Passengers;
import com.irctc.station.entity.Station;
import com.irctc.train.entity.Train;
import com.irctc.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"booking","booking2"})
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"route","booking","booking2"})
    private Station fromstation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"route","booking","booking2"})
    private Station tostation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"route","booking","bookings"})
    private Train train;

    private int amountPaid;
    private int numberOfTickets;
    private String seat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date travelDate;

    @OneToMany( mappedBy = "booking",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties({"booking","bookings"})
    private List<Passengers> passengers;


}
