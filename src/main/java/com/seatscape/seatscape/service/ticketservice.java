package com.seatscape.seatscape.service;
import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.dao.ticketDAO;
import com.seatscape.seatscape.exceptions.*;
import com.seatscape.seatscape.model.ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
//
//    public ResponseEntity<String> partialcanellation(ticket ticket) throws InvalidTicketException {
//        int ticketID = ticket.getTicketid();
//        Optional<ticket> tfromDB = ticketDAO.findById(ticketID);
//        if(tfromDB.isPresent()){
//            if(ticket.getNumberofseats()<tfromDB.get().getNumberofseats()){
//                int [] seatstocancel = ticket.getBookedseats();
//                StringBuilder sb = new StringBuilder(showDAO.getBookedSeatsbyshowid(ticket.getShowid()));
//                int [] bookedseats = tfromDB.get().getBookedseats();
//                Arrays.sort(bookedseats);
//                ArrayList<Integer> listInvalid = new ArrayList<>();
//                for(int seat : seatstocancel){
//                    if(Arrays.binarySearch(bookedseats, seat)==-1){
//                        listInvalid.add(seat);
//                    }
//                }
//                if(listInvalid.isEmpty()){
//                    for(int seat : seatstocancel){
//                        sb.setCharAt(2 * seat,'0');
//                    }
//                    Arrays.sort(seatstocancel);
//                    ArrayList<Integer> resSeats = new ArrayList<>();
//                    int cntT = 0;
//                    for(int seat : bookedseats){
//                        if(Arrays.binarySearch(seatstocancel, seat)==-1){
//                            resSeats.add(seat);
//                        }else cntT++;
//                    }
//                    int [] resultseats = new int[resSeats.size()];
//                    int i = 0;
//                    for(int k = 0;k<resSeats.size();k++){
//                        resultseats[i] = resSeats.get(k);
//                    }
//                    int remSeats  = tfromDB.get().getNumberofseats() - ticket.getNumberofseats();
//                    showDAO.updateseats(tfromDB.get().getShowid(), tfromDB.get().getNumberofseats() + ticket.getNumberofseats());
//                    showDAO.setbookedseats(sb.toString(), tfromDB.get().getShowid());
//                    ticketDAO.updateBookedseats(resultseats, ticketID);
//                    ticketDAO.setticketcount(remSeats, ticketID);
//                    return new ResponseEntity<>("SUCCESFULLY CANCELLED", HttpStatus.OK);
//                }else{
//                    return new ResponseEntity<>("The Seats"+listInvalid.toString()+ "is not booked by You, Please don't be Extra Smart.", HttpStatus.BAD_REQUEST);
//                }
//            }else if (ticket.getNumberofseats()==tfromDB.get().getNumberofseats()){
//                return new ResponseEntity<>("Since The Number of Supplied Tickets is Same as the Number of Seats Reserved, Please go through Complete Cancellation", HttpStatus.BAD_REQUEST);
//            }else{
//                return new ResponseEntity<>("The Number of Seats Cancelling Exceeds the number of Booked Seats", HttpStatus.BAD_REQUEST);
//            }
//        }else{
//            throw new InvalidTicketException("The Ticket You're Trying to Cancel is Invalid.");
//        }
//    }
    public ResponseEntity<String> partialCancellation(ticket ticket) throws InvalidTicketException {
        int ticketID = ticket.getTicketid();
        Optional<ticket> optionalTicketFromDB = ticketDAO.findById(ticketID);
        if (optionalTicketFromDB.isPresent()) {
            ticket ticketFromDB = optionalTicketFromDB.get();
            int requestedSeats = ticket.getNumberofseats();
            if (requestedSeats < ticketFromDB.getNumberofseats()) {
                int[] seatsToCancel = ticket.getBookedseats();
                String bookedSeatsString = showDAO.getBookedSeatsbyshowid(ticket.getShowid());
                int[] bookedSeats = ticketFromDB.getBookedseats();
                Arrays.sort(bookedSeats);
                List<Integer> invalidSeats = new ArrayList<>();
                StringBuilder sb = new StringBuilder(bookedSeatsString);
                for (int seat : seatsToCancel) {
                    if (Arrays.binarySearch(bookedSeats, seat) == -1) {
                        invalidSeats.add(seat);
                    } else {
                        sb.setCharAt(2 * seat, '0');
                    }
                }
                if (invalidSeats.isEmpty()) {
                    Arrays.sort(seatsToCancel);
                    List<Integer> remainingSeats = new ArrayList<>();
                    for (int seat : bookedSeats) {
                        if (Arrays.binarySearch(seatsToCancel, seat) == -1) {
                            remainingSeats.add(seat);
                        }
                    }
                    int[] resultSeats = remainingSeats.stream().mapToInt(Integer::intValue).toArray();
                    int remainingSeatCount = ticketFromDB.getNumberofseats() - requestedSeats;
                   try{
                       synchronized (o){
                           showDAO.updateseats(ticketFromDB.getShowid(), showDAO.getAvailablesetsfromshowid(ticket.getShowid()) + requestedSeats);
                           showDAO.setbookedseats(sb.toString(), ticketFromDB.getShowid());
                           ticketDAO.updateBookedseats(resultSeats, ticketFromDB.getTicketid());
                           ticketDAO.setticketcount(remainingSeatCount, ticketFromDB.getTicketid());
                       }
                       return new ResponseEntity<>("SUCCESSFULLY CANCELLED", HttpStatus.OK);
                   }catch (Exception e){
                       e.printStackTrace();
                       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                   }
                } else {
                    return new ResponseEntity<>(
                            "The Seats " + invalidSeats.toString() + " are not booked by You. Please don't be Extra Smart.",
                            HttpStatus.BAD_REQUEST);
                }
            } else if (requestedSeats == ticketFromDB.getNumberofseats()) {
                return new ResponseEntity<>(
                        "Since the number of supplied tickets is the same as the number of seats reserved, please go through complete cancellation.",
                        HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("The number of seats cancelling exceeds the number of booked seats.",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new InvalidTicketException("The ticket you're trying to cancel is invalid.");
        }
    }



    public ResponseEntity<List<Integer>> avlseatsforshowid(Integer showid) {
        StringBuilder sb = new StringBuilder(showDAO.getBookedSeatsbyshowid(showid));
        List<Integer> ls = new ArrayList<>();
        System.out.println(sb.toString());
        for(int i = 1;i<=99;i++){
            if(sb.charAt(i*2)=='0')ls.add(i);
        }
        try{
            return new ResponseEntity<>(ls, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
