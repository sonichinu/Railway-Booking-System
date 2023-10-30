package com.irctc.seats.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.train.entity.Train;
//import jakarta.persistence.*;
//
//@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//public class Seats {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    private int availableSeats;
//    private int availableSleepers;
//    private int availableThirdAc;
//    private int availableSecondAc;
//    private int availableFirstAc;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("seats")
//    private Train train;
//
//
//}
