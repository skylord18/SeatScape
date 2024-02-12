package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.fooditem;
import com.seatscape.seatscape.service.fooditemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class fooditemcontroller {
    @Autowired
    fooditemservice fooditemservice;

    @GetMapping("all")
    public ResponseEntity<List<fooditem>> getallfooditems(){
        return fooditemservice.getallfooditems();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<fooditem>> getfooditembyid(@PathVariable("id") Integer id){
        return fooditemservice.getfooditembyid(id);
    }
    @GetMapping("itemtype/{itemtype}")
    public ResponseEntity<List<fooditem>> getfooditemsbytype(@PathVariable("itemtype") String itemtype){
        return fooditemservice.getfooditemsbytype(itemtype);
    }
    @PutMapping("add")
    public ResponseEntity<fooditem> addfoodItem(@RequestBody fooditem fooditem){
        return fooditemservice.addfoodItem(fooditem);
    }
}
