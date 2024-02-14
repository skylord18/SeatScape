package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.foodorderDAO;
import com.seatscape.seatscape.dao.ticketDAO;
import com.seatscape.seatscape.exceptions.FoodOrderAlreadyExistsException;
import com.seatscape.seatscape.exceptions.FoodOrderEmptyException;
import com.seatscape.seatscape.exceptions.TicketDoesNotExistException;
import com.seatscape.seatscape.model.foodorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class foodorderservice {
    @Autowired
    foodorderDAO foodorderDAO;
    @Autowired
    fooditemservice fooditemservice;
    @Autowired
    ticketDAO ticketDAO;

    public ResponseEntity<List<foodorder>> getallorders() {
        try{
            return new ResponseEntity<>(foodorderDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<foodorder>> getfoodorderbyticketid(Integer ticketid) {
        try{
            return new ResponseEntity<>(Optional.ofNullable(foodorderDAO.findByTicketid(ticketid)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<foodorder>> getfoodorderbyid(Integer id) {
        try{
            Optional<foodorder> o = foodorderDAO.findById(id);
            return new ResponseEntity<>(o, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<foodorder>> getfoodordersbyshowid(Integer showid) {
        try{
            return new ResponseEntity<>(foodorderDAO.findByShowid(showid), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<foodorder> placefoodorder(foodorder foodorder) throws FoodOrderAlreadyExistsException, FoodOrderEmptyException, TicketDoesNotExistException {
       if(foodorder.getItems().length==0)throw new FoodOrderEmptyException("The Items in your order are Empty, Please add some items and try again.");
       if(ticketDAO.findById(foodorder.getTicketid()).isEmpty())throw new TicketDoesNotExistException("The Ticket ID supplied is invalid, Please Check and Retry.");
       if(foodorderDAO.findByTicketid(foodorder.getTicketid())==null){
            foodorder.setPaid(false);
            int orderValue = 0;
            for(int val : foodorder.getItems())orderValue+=fooditemservice.getpriceforitem(val);
            foodorder.setTotalprice(orderValue);
            System.out.println(foodorder.toString());
            try{
                foodorderDAO.save(foodorder);
                return new ResponseEntity<>(foodorder, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
       }else throw new FoodOrderAlreadyExistsException("A Food Order Already exists for this Ticket ID, As per the policy, You can only place one order per ticket.");
    }
}
