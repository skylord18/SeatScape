package com.seatscape.seatscape.service;

import com.seatscape.seatscape.dao.showDAO;
import com.seatscape.seatscape.model.show;
import com.seatscape.seatscape.model.showwrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class showwrapperservice {
    @Autowired
    showDAO showDAO;

    public ResponseEntity<String> addtoDB(showwrapper showwrapper) {
        Integer showid = showwrapper.getShowid(), hallid = showwrapper.getHallid(), cinemaid = showwrapper.getCinemaid(), movieid = showwrapper.getMovieid();
        Integer avlseats = showwrapper.getAvailableseats(), showYear = showwrapper.getStartyear(), showMonth = showwrapper.getStartmonth(), showDate = showwrapper.getStartday();
        Integer startHour   = showwrapper.getStarthour(), startMin = showwrapper.getStartmin();
        Timestamp ts = Timestamp.valueOf(LocalDateTime.of(showYear, showMonth, showDate, startHour,startMin));
        show s = new show(showid,cinemaid, hallid, movieid, avlseats, ts);
        System.out.println("Obj Generated");
        try{
            showDAO.save(s);
            System.out.println("Obj SAVED");
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("CANNOT SAVE", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
