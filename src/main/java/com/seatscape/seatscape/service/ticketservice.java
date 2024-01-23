package com.seatscape.seatscape.service;
import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.dao.ticketDAO;
import com.seatscape.seatscape.exceptions.CountMismatchException;
import com.seatscape.seatscape.exceptions.HouseFullException;
import com.seatscape.seatscape.exceptions.InsufficientTicketsException;
import com.seatscape.seatscape.exceptions.SeatAlreadyBookedException;
import com.seatscape.seatscape.model.seat;
import com.seatscape.seatscape.model.ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ticketservice {
    @Autowired
    ticketDAO ticketDAO;   //-->Create and persist the ticket
    @Autowired
    showDAO showDAO; //-->Enquire the count of available tickets, reduce the count of avlbl tickets

    public ResponseEntity<Optional<ticket>> createTicket(ticket ticket) throws HouseFullException, InsufficientTicketsException, SeatAlreadyBookedException, CountMismatchException {
        int avlT = showDAO.getAvailablesetsfromshowid(ticket.getShowid());
        if(avlT == 0)throw new HouseFullException("The Show is housefull, Try with another show.");
        if(avlT < ticket.getNumberofseats())throw new InsufficientTicketsException("Only " + avlT  + " tickets are available, Try with fewer number of seats.");
        if(ticket.getNumberofseats()!=ticket.getBookedseats().length)throw new CountMismatchException("There seems to be a problem with count of tickets and number of selected seats.");
        String bookedseats = showDAO.getBookedSeatsbyshowid(ticket.getShowid());
        System.out.println(bookedseats.length());
        System.out.println(bookedseats);
        for(Integer seatid : ticket.getBookedseats()){
            if(bookedseats.charAt(2 * seatid)!= '0')throw new SeatAlreadyBookedException("The seat you re trying to book is already booked. Please check with another seats.");
        }
        StringBuilder sb = new StringBuilder(bookedseats);
        for(Integer seatid : ticket.getBookedseats()) {
            sb.setCharAt(2* seatid, '1');
        }
        System.out.println(sb.toString());
//        for(Integer seatid : ticket.getBookedseats()){
//            if(bookedseats[0]!=0)throw new SeatAlreadyBookedException("The seat you re trying to book is already booked. Please check with another seats.");
//        }
//        for(Integer seatid : ticket.getBookedseats()){
//            bookedseats[seatid] = 1;
//        }
        try{
            showDAO.reduceseatsbycount(ticket.getShowid(), avlT - ticket.getNumberofseats());
            showDAO.setbookedseats(sb.toString(), ticket.getShowid());
            ticketDAO.save(ticket);
           return new ResponseEntity<>(Optional.of(ticket), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getallbyshowid(Integer showid) {
        try{
            return new ResponseEntity<>(ticketDAO.getallbyshowid(showid), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<ticket>> getticketbyticketid(Integer ticketid) {
        try{
            return new ResponseEntity<>(Optional.of(ticketDAO.getticketbyticketid(ticketid)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getticketsbyusername(String username) {
        try{
            return new ResponseEntity<>(ticketDAO.getticketsbyusername(username), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getalltickets() {
        try{
            return new ResponseEntity<>(ticketDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
