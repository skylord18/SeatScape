package com.seatscape.seatscape.service;
import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.dao.ticketDAO;
import com.seatscape.seatscape.exceptions.HouseFullException;
import com.seatscape.seatscape.exceptions.InsufficientTicketsException;
import com.seatscape.seatscape.model.ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ticketservice {
    @Autowired
    ticketDAO ticketDAO;   //-->Create and persist the ticket
    @Autowired
    showDAO showDAO; //-->Enquire the count of available tickets, reduce the count of avlbl tickets

    public ResponseEntity<Optional<ticket>> createTicket(ticket ticket) throws HouseFullException, InsufficientTicketsException {
        // Reduce the available number of tickets for the show
        // Create the ticket and persist it in the database
        Integer availableSeats = showDAO.getAvailablesetsfromshowid(ticket.getShowid());
        System.out.println("Avl Seats: " + availableSeats);
        // Check if the show is house-full
        if (availableSeats <= 0)throw new HouseFullException("The show is house-full. Cannot create ticket.");
        // Check if there are enough available seats for the requested tickets
        if (ticket.getNumberofseats() > availableSeats)throw new InsufficientTicketsException("Insufficient tickets available for the show.");
        try{
            showDAO.reduceseatsbycount(ticket.getShowid(),availableSeats - ticket.getNumberofseats());
            ticket t = new ticket(ticket.getShowid(), ticket.getNumberofseats(), ticket.getBookedby());
            try{
                System.out.println("Ticket Generated");
                ticketDAO.save(t);
                return new ResponseEntity<>(Optional.of(t), HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Unable to save");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unable to reduce seat count");
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
}
