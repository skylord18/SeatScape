package com.seatscape.seatscape.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketid;
    private Integer showid;
    private Integer numberofseats;
    private String bookedby;

    public ticket(){

    }

    public ticket(Integer showid, Integer numberofseats, String bookedby) {
        this.showid = showid;
        this.numberofseats = numberofseats;
        this.bookedby = bookedby;
    }

    public ticket(Integer ticketid, Integer showid, Integer numberofseats, String bookedby) {
        this.ticketid = ticketid;
        this.showid = showid;
        this.numberofseats = numberofseats;
        this.bookedby = bookedby;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "ticketid=" + ticketid +
                ", showid=" + showid +
                ", numberofseats=" + numberofseats +
                ", bookedby='" + bookedby + '\'' +
                '}';
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid() {
        this.ticketid = ticketid;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public Integer getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(Integer numberofseats) {
        this.numberofseats = numberofseats;
    }

    public String getBookedby() {
        return bookedby;
    }

    public void setBookedby(String bookedby) {
        this.bookedby = bookedby;
    }
}
