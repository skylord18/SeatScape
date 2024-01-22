package com.seatscape.seatscape.controller;

import com.seatscape.seatscape.model.showwrapper;
import com.seatscape.seatscape.service.showwrapperservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class showwrappercontroller {
    @Autowired
    showwrapperservice showwrapperservice;
    @PutMapping("add")
    public ResponseEntity<String> addShow(@RequestBody showwrapper showwrapper){
        return showwrapperservice.addtoDB(showwrapper);
    }
}
