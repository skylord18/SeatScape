package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "foodorders")
public class foodorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ticketid;
    private String name;
    private Integer showid;
    private int[] items;
    private Integer totalprice;
    private Boolean isPaid;

    public foodorder(Integer ticketid, String name, Integer showid, int[] items, Integer totalprice, Boolean isPaid) {
        this.ticketid = ticketid;
        this.name = name;
        this.showid = showid;
        this.items = items;
        this.totalprice = totalprice;
        this.isPaid = isPaid;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShowid() {
        return showid;
    }

    public void setShowid(Integer showid) {
        this.showid = showid;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
