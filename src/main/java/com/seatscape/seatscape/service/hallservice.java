package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.hallDAO;
import com.seatscape.seatscape.model.cinema;
import com.seatscape.seatscape.model.hall;
import com.seatscape.seatscape.model.seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class hallservice {
    @Autowired
    cinemaservice cinemaservice;
    @Autowired
    hallDAO hallDAO;
    Object o = new Object();
    public ResponseEntity<List<hall>> getallHalls() {
        try{
            synchronized (o){
                return new ResponseEntity<>(hallDAO.findAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> createHalls(Integer cinemaid) {
        ResponseEntity<Optional<cinema>> c = cinemaservice.getbyid(cinemaid);
        if(c.getBody().isPresent()){
            Integer totalHalls = c.getBody().get().getTotalhalls();
            List<hall> listHalls = new ArrayList<>();
            for(int i = 1;i<=totalHalls;i++){
                listHalls.add(new hall(10 * cinemaid + i , cinemaid, 99));
            }
            try{
                synchronized (o){
                    hallDAO.saveAll(listHalls);
                    return new ResponseEntity<>("Success", HttpStatus.CREATED);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>("List Created but cannot save", HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>("Cinema ID not Present", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<hall>> gethallsbycinemaid(Integer cinemaid) {
        try{
            synchronized (o){
                return new ResponseEntity<>(hallDAO.findAllBycinemaid(cinemaid), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
