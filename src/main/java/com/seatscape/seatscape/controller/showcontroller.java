package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.show;
import com.seatscape.seatscape.service.showservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("show")
public class showcontroller {
    @Autowired
    showservice showservice;
    @GetMapping("all")
    public ResponseEntity<List<show>> getallshows(){
        return showservice.getallshows();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<show>> getshowbyid(@PathVariable("id") Integer id){
        return showservice.getshowbyid(id);
    }
    //By CinemaID
    @GetMapping("cid/{cinemaid}")
    public ResponseEntity<List<show>> getshowsbycinemaid(@PathVariable("cinemaid") Integer cinemaid){
        return showservice.getbycinemaid(cinemaid);
    }
    //By movieId
    @GetMapping("mid/{movieid}")
    public ResponseEntity<List<show>> getshowbymovieid(@PathVariable("movieid") Integer movieid){
        return showservice.getbymovieid(movieid);
    }
    //SHOWS NOT YET STARTED
    @GetMapping("future")
    public ResponseEntity<List<show>> getfutureshows(){
        return showservice.getfutureshows();
    }
    //SHOWS NOT YET STARTED BY CINEMAID
    @GetMapping("future/{cinemaid}")
    public ResponseEntity<List<show>> getfutureshowsbycinemaid(@PathVariable("cinemaid") Integer cinemaid){
        return showservice.getfutureshowsbycinemaid(cinemaid);
    }
    //SHOWS NOT YET STARTED BY MOVIEID
    @GetMapping("future/mid/{movieid}")
    public ResponseEntity<List<show>> getfutureshowsbymovieid(@PathVariable("movieid") Integer movieid){
        return showservice.getfutureshowsbymovieid(movieid);
    }
    //SHOWS BY CITY NAME
    @GetMapping("city/{cityname}")
    public ResponseEntity<List<show>> getshowsbycityname(@PathVariable("cityname") String cityname){
        return showservice.getshowsbycityname(cityname);
    }
    @GetMapping("citymovie/{cityname}/{moviename}")
    public ResponseEntity<List<show>> getshowsbycityandmoviename(@PathVariable("cityname") String cityname, @PathVariable("moviename") String moviename){
        System.out.println(cityname + " " + moviename);
        return showservice.getShowsByCityAndMovieName(cityname, moviename);
    }
}
