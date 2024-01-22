package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.exceptions.*;

import com.seatscape.seatscape.model.ticket;
import com.seatscape.seatscape.service.ticketservice;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ticket")
public class ticketcontroller {
    @Autowired
    ticketservice ticketservice;
    //Create
        @PutMapping("create")
        public ResponseEntity<Optional<ticket>> createticket(@RequestBody ticket ticket) throws Exception{
           return ticketservice.createTicket(ticket);
        }
    //Read
        //By Show-->
        @GetMapping("getall")
        public ResponseEntity<List<ticket>> getalltickets(){
            return ticketservice.getalltickets();
        }
        @GetMapping("{showid}")
        public ResponseEntity<List<ticket>> getallbyshowid(@PathVariable("showid") Integer showid){
            return ticketservice.getallbyshowid(showid );
        }
        //By Ticked ID
        @GetMapping("id/{ticketid}")
        public ResponseEntity<Optional<ticket>> getticketbyticketid(@PathVariable("ticketid") Integer ticketid){
            return ticketservice.getticketbyticketid(ticketid);
        }

        //Using Booked By
        @GetMapping("user/{username}")
        public ResponseEntity<List<ticket>> getticketsbyusername(@PathVariable("username") String username){
            return ticketservice.getticketsbyusername(username);
        }

}
