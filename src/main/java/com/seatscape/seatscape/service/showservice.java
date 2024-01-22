package com.seatscape.seatscape.service;
import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.model.show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class showservice {
    @Autowired
    showDAO showDAO;

    public ResponseEntity<List<show>> getallshows() {
       try{
           return new ResponseEntity<>(showDAO.findAll(), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<show>> getshowbyid(Integer id) {
        try {
            Optional<show> optionalShow = showDAO.findById(id);
            if (optionalShow.isPresent()) {
                return new ResponseEntity<>(optionalShow, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<show>> getbycinemaid(Integer cinemaid) {
        try{
            List<show>tempList = showDAO.findBycinemaid(cinemaid);
            if(!tempList.isEmpty()){
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<List<show>> getbymovieid(Integer movieid) {
        try{
            List<show> tempList = showDAO.getbymovieid(movieid);
            if(!tempList.isEmpty()){
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<show>> getfutureshowsbycinemaid(Integer cinemaid) {
        try{
            List<show> tempList = showDAO.getfutureshowsbycinemaid(Timestamp.valueOf(LocalDateTime.now()), cinemaid);
            if(!tempList.isEmpty()){
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<show>> getfutureshows() {
        try{
            List<show> tempList = showDAO.getfutureshows(Timestamp.valueOf(LocalDateTime.now()));
            if(!tempList.isEmpty()){
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<show>> getfutureshowsbymovieid(Integer movieid) {
        try{
            List<show> tempList = showDAO.getfutureshowsbymovieid(Timestamp.valueOf(LocalDateTime.now()), movieid);
            if(!tempList.isEmpty()){
                return new ResponseEntity<>(tempList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<show>> getshowsbycityname(String cityname) {
        try{
            return new ResponseEntity<>(showDAO.getShowsByCityName(cityname), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<show>> getShowsByCityAndMovieName(String cityname, String moviename) {
        try{
            return new ResponseEntity<>(showDAO.getShowsByCityAndMovieName(cityname, moviename), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
