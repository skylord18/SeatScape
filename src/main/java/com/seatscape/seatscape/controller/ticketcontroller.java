package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.exceptions.*;
import com.seatscape.seatscape.model.ticket;
import com.seatscape.seatscape.service.ticketservice;
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
        @PutMapping("create")
        public ResponseEntity<Optional<ticket>> createticket(@RequestBody ticket ticket) throws HouseFullException, InsufficientTicketsException, SeatAlreadyBookedException, CountMismatchException, CountOfSeatsZero, TooManySeatsException {
           return ticketservice.createTicket(ticket);
        }
        @GetMapping("getall")
        public ResponseEntity<List<ticket>> getalltickets(){
            return ticketservice.getalltickets();
        }
        @GetMapping("showid/{showid}")
        public ResponseEntity<List<ticket>> getallbyshowid(@PathVariable("showid") Integer showid){
            return ticketservice.getallbyshowid(showid );
        }
        @GetMapping("id/{ticketid}")
        public ResponseEntity<Optional<ticket>> getticketbyticketid(@PathVariable("ticketid") Integer ticketid){
            return ticketservice.getticketbyticketid(ticketid);
        }
        @GetMapping("user/{username}")
        public ResponseEntity<List<ticket>> getticketsbyusername(@PathVariable("username") String username){
            return ticketservice.getticketsbyusername(username);
        }
        @DeleteMapping("cancel/{id}")
        public ResponseEntity<String> cancelTicket(@PathVariable("id") Integer id) throws TicketDoesNotExistException, SeatsareInconsistentStateException {
            return ticketservice.cancelTicket(id);
        }
        @DeleteMapping("cancel/partial")
        public ResponseEntity<String> cancelPartial(@RequestBody ticket ticket) throws InvalidTicketException {
            return ticketservice.partialCancellation(ticket);
        }
        @GetMapping("show/{showid}")
        public ResponseEntity<List<Integer>> avlseatsforshowid(@PathVariable("showid") Integer showid){
            return ticketservice.avlseatsforshowid(showid);
        }
}
