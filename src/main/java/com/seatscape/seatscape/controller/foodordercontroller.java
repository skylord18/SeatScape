package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.exceptions.FoodOrderAlreadyExistsException;
import com.seatscape.seatscape.exceptions.FoodOrderEmptyException;
import com.seatscape.seatscape.exceptions.TicketDoesNotExistException;
import com.seatscape.seatscape.model.foodorder;
import com.seatscape.seatscape.model.foodorderwrapper;
import com.seatscape.seatscape.service.foodorderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("foodorder")
public class foodordercontroller {
    @Autowired
    foodorderservice foodorderservice;
    @GetMapping("getall")
    public ResponseEntity<List<foodorder>> getallfoodorders(){
        return foodorderservice.getallorders();
    }
    @GetMapping("ticketid/{ticketid}")
    public ResponseEntity<Optional<foodorder>> getfoodorderbyticketid(@PathVariable("ticketid") Integer ticketid){
        return foodorderservice.getfoodorderbyticketid(ticketid);
    }
    @GetMapping("id")
    public ResponseEntity<Optional<foodorder>> getfoodorderbyid(@PathVariable("id") Integer id){
        return foodorderservice.getfoodorderbyid(id);
    }
    @GetMapping("showid/{showid}")
    public ResponseEntity<List<foodorder>> getfoodordersbyshowid(@PathVariable("showid") Integer showid){
        return foodorderservice.getfoodordersbyshowid(showid);
    }
    @PutMapping("placeorder")
    public ResponseEntity<foodorder> placefoodorder(@RequestBody foodorder foodorder) throws FoodOrderAlreadyExistsException, FoodOrderEmptyException, TicketDoesNotExistException {
        return foodorderservice.placefoodorder(foodorder);
    }

}
