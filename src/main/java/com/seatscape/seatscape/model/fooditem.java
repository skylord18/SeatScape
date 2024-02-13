package com.seatscape.seatscape.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fooditems")
public class fooditem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String itemType;

    public fooditem() {
    }

    public fooditem(String name, String description, Integer price, String itemType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.itemType = itemType;
    }
}
