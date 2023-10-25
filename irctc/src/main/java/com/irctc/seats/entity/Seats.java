//package com.irctc.seats.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.irctc.Train.Entity.Train;
//import jakarta.persistence.*;
//
//@Entity
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
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"route","booking","bookings"})
//    private Train train;
//
//
//}
