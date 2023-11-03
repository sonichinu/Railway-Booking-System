package com.irctc.dto;

import com.irctc.passengers.entity.Passengers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailsDto {
    private String userName;
    private String trainName;
    private String fromStation;
    private String toStation;
    private int amountPaid;
    private int noOfPassengers;
    private List<Passengers> plist;
    private Date travelDate;

}
