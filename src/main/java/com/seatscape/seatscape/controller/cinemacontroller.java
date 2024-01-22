package com.seatscape.seatscape.controller;
import com.seatscape.seatscape.model.cinema;
import com.seatscape.seatscape.service.cinemaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("cinema")
public class cinemacontroller {
    @Autowired
    cinemaservice cinemaservice;
    @GetMapping("all")
    public ResponseEntity<List<cinema>> getAll(){
        return cinemaservice.getAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<cinema>> getcinemabyid(@PathVariable("id") Integer id){
        return cinemaservice.getbyid(id);
    }
    @GetMapping("city/{cityname}")
    public ResponseEntity<List<cinema>> getbycity(@PathVariable("cityname") String cityname){
        return cinemaservice.getcinemasbycity(cityname);
    }
    @GetMapping("state/{statename}")
    public ResponseEntity<List<cinema>> getbystate(@PathVariable("statename") String statename){
        return cinemaservice.getcinemasbystate(statename);
    }
    @GetMapping("{statename}/{cityname}")
    public ResponseEntity<List<cinema>> getbystatecity(@PathVariable("statename") String statename, @PathVariable("cityname") String cityname){
        return cinemaservice.getbystatecity(statename,cityname);
    }

}
