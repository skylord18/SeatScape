package com.seatscape.seatscape.service;
import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.dao.ticketDAO;
import com.seatscape.seatscape.exceptions.*;
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
    final Object o = new Object();
    public ResponseEntity<Optional<ticket>> createTicket(ticket ticket) throws HouseFullException, InsufficientTicketsException, SeatAlreadyBookedException, CountMismatchException, CountOfSeatsZero {
        if(ticket.getNumberofseats() == 0 )throw new CountOfSeatsZero("The count of tickets is Zero, Plaese add some seats and try again.");
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
        try{
            synchronized (o){
                showDAO.updateseats(ticket.getShowid(), avlT - ticket.getNumberofseats());
                showDAO.setbookedseats(sb.toString(), ticket.getShowid());
                ticketDAO.save(ticket);
                return new ResponseEntity<>(Optional.of(ticket), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getallbyshowid(Integer showid) {
        try{
            synchronized (o){
                return new ResponseEntity<>(ticketDAO.getallbyshowid(showid), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<ticket>> getticketbyticketid(Integer ticketid) {
        try{
            synchronized (o){
                return new ResponseEntity<>(Optional.of(ticketDAO.getticketbyticketid(ticketid)), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getticketsbyusername(String username) {
        try{
            synchronized (o){
                return new ResponseEntity<>(ticketDAO.getticketsbyusername(username), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ticket>> getalltickets() {
        try{
            synchronized (o){
                return new ResponseEntity<>(ticketDAO.findAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> cancelTicket(Integer id) throws TicketDoesNotExistException, SeatsareInconsistentStateException {
        Optional<ticket> ticket = ticketDAO.findById(id);
        if(ticket.isPresent()){
            int showid = ticket.get().getShowid(), numSeats = ticket.get().getNumberofseats(), showID = ticket.get().getShowid(), currFreeSeats = showDAO.getAvailablesetsfromshowid(showid);
            int[] seatstoFree = ticket.get().getBookedseats();
            String seatsbooked = showDAO.getBookedSeatsbyshowid(showID);
            StringBuilder sb = new StringBuilder(seatsbooked);
            for(int bookedseat : seatstoFree){
                if(sb.charAt(2 * bookedseat) != '1'){
                    throw new SeatsareInconsistentStateException("The Seats are in Inconsistent State, Requires Immediate Attention.");
                }else{
                    sb.setCharAt(2 * bookedseat, '0');
                }
            }
            try{
                synchronized (o){
                    ticketDAO.deleteById(id);
                    showDAO.updateseats(showid, currFreeSeats + numSeats);
                    showDAO.setbookedseats(sb.toString(), showid);
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new TicketDoesNotExistException("The Ticket that you're cancelling does not exist, please try with a valid ticket ID.");
        }
    }
}
