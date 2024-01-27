package com.seatscape.seatscape.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
@Entity
@Data
public class showwrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showid;
    private Integer cinemaid;
    private Integer hallid;
    private Integer movieid;
    private Integer availableseats;
    private Integer startyear;
    private Integer startmonth;
    private Integer startday;
    private Integer starthour;
    private Integer startmin;

    private String seats;

    public showwrapper(Integer showid, Integer cinemaid, Integer hallid, Integer movieid, Integer availableseats, Integer startyear, Integer startmonth, Integer startday, Integer starthour, Integer startmin, String seats) {
        this.showid = showid;
        this.cinemaid = cinemaid;
        this.hallid = hallid;
        this.movieid = movieid;
        this.availableseats = availableseats;
        this.startyear = startyear;
        this.startmonth = startmonth;
        this.startday = startday;
        this.starthour = starthour;
        this.startmin = startmin;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "showwrapper{" +
                "showid=" + showid +
                ", cinemaid=" + cinemaid +
                ", hallid=" + hallid +
                ", movieid=" + movieid +
                ", availableseats=" + availableseats +
                ", startyear=" + startyear +
                ", startmonth=" + startmonth +
                ", startday=" + startday +
                ", starthour=" + starthour +
                ", startmin=" + startmin +
                ", seats=" + seats +
                '}';
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

    public Integer getAvailableseats() {
        return availableseats;
    }

    public void setAvailableseats(Integer availableseats) {
        this.availableseats = availableseats;
    }

    public Integer getStartyear() {
        return startyear;
    }

    public void setStartyear(Integer startyear) {
        this.startyear = startyear;
    }

    public Integer getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(Integer startmonth) {
        this.startmonth = startmonth;
    }

    public Integer getStartday() {
        return startday;
    }

    public void setStartday(Integer startday) {
        this.startday = startday;
    }

    public Integer getStarthour() {
        return starthour;
    }

    public void setStarthour(Integer starthour) {
        this.starthour = starthour;
    }

    public Integer getStartmin() {
        return startmin;
    }

    public void setStartmin(Integer startmin) {
        this.startmin = startmin;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
