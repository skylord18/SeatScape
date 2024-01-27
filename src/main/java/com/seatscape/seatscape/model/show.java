package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data

public class show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showid;
    private Integer cinemaid;
    private Integer hallid;
    private Integer movieid;
    @Column(name = "availableseats")
    private Integer avlseats;
    private Timestamp starttime;
    @Column(name = "bookedtickets")
    private String bookedtickets;
    public show(){

    }
    public show(Integer cinemaid, Integer hallid, Integer movieid, Integer avlseats, Timestamp starttime) {
        this.cinemaid = cinemaid;
        this.hallid = hallid;
        this.movieid = movieid;
        this.avlseats = avlseats;
        this.starttime = starttime;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public Integer getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Integer cinemaid) {
        this.cinemaid = cinemaid;
    }

    public Integer getHallid() {
        return hallid;
    }

    public void setHallid(Integer hallid) {
        this.hallid = hallid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer getAvlseats() {
        return avlseats;
    }

    public void setAvlseats(Integer avlseats) {
        this.avlseats = avlseats;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public String getBookedtickets() {
        return bookedtickets;
    }

    public void setBookedtickets(String bookedtickets) {
        this.bookedtickets = bookedtickets;
    }
}
