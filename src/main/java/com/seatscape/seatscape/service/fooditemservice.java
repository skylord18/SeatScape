package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.fooditemDAO;
import com.seatscape.seatscape.model.fooditem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class fooditemservice {
    @Autowired
    fooditemDAO fooditemDAO;
    public ResponseEntity<List<fooditem>> getallfooditems() {
        try{
            return new ResponseEntity<>(fooditemDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<fooditem>> getfooditembyid(Integer id) {
        try{
            Optional<fooditem>  op = fooditemDAO.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<fooditem>> getfooditemsbytype(String itemtype) {
        try{
            return new ResponseEntity<>(fooditemDAO.findByitemtype(itemtype), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<fooditem> addfoodItem(fooditem fooditem) {
        try{
            fooditemDAO.save(fooditem);
            return new ResponseEntity<>(fooditem,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public int getpriceforitem(int val) {
        int prc = fooditemDAO.getPricebyitemid(val);
        return prc;
    }
}
