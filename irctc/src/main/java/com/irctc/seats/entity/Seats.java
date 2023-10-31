package com.irctc.seats.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.irctc.train.entity.Train;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int totalChairCars;
    private int totalSleepers;
    private int totalThirdAc;
    private int totalSecondAc;
    private int totalFirstAc;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"seats","route","booking"})
    private Train train;

    @Override
    public String toString() {
        return "Seats{" +
                "id=" + id +
                ", totalChairCars=" + totalChairCars +
                ", totalSleepers=" + totalSleepers +
                ", totalThirdAc=" + totalThirdAc +
                ", totalSecondAc=" + totalSecondAc +
                ", totalFirstAc=" + totalFirstAc +
                ", train=" + train +
                '}';
    }
}
