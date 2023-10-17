package com.irctc.Bookings.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.Passengers.Entity.Passengers;
import com.irctc.Station.Entity.Station;
import com.irctc.Train.Entity.Train;
import com.irctc.User.Entity.User;
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

    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"route"})
    private Station fromstation;

    @ManyToOne(cascade = CascadeType.ALL)
    private Station tostation;

    @ManyToOne(cascade = CascadeType.ALL)
    private Train train;

    private int amountPaid;
    private int numberOfTickets;
    private String seat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date travelDate;

    @OneToMany( mappedBy = "booking",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnoreProperties({"booking"})
    private List<Passengers> passengers;


}
