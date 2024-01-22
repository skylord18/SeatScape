package com.seatscape.seatscape.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Data

public class show {
    @Id
    private Integer showid;
    private Integer cinemaid;
    private Integer hallid;
    private Integer movieid;
    @Column(name = "availableseats")
    private Integer avlseats;
    private Timestamp starttime;
    public show(){

    }

    public show(Integer showid, Integer cinemaid, Integer hallid, Integer movieid, Integer avlseats, Timestamp starttime) {
        this.showid = showid;
        this.cinemaid = cinemaid;
        this.hallid = hallid;
        this.movieid = movieid;
        this.avlseats = avlseats;
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "show{" +
                "showid=" + showid +
                ", cinemaid=" + cinemaid +
                ", hallid=" + hallid +
                ", movieid=" + movieid +
                ", avlseats=" + avlseats +
                ", starttime=" + starttime +
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
}
