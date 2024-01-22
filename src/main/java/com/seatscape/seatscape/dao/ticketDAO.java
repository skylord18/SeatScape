package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ticketDAO extends JpaRepository<ticket, Integer> {
    @Query(value = "SELECT * FROM ticket t where t.showid = :showid", nativeQuery = true)
    List<ticket> getallbyshowid(Integer showid);
    @Query(value = "SELECT * FROM ticket t where t.ticketid = :ticketid", nativeQuery = true)
    ticket getticketbyticketid(Integer ticketid);
    @Query(value = "SELECT * FROM ticket t where t.bookedby = :username", nativeQuery = true)
    List<ticket> getticketsbyusername(String username);
}
