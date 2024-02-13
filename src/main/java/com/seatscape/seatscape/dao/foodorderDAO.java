package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.foodorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface foodorderDAO extends JpaRepository<foodorder, Integer> {
    @Query(value = "SELECT * from foodorders f WHERE f.ticketid = :ticketid LIMIT 1", nativeQuery = true)
    foodorder findByTicketid(Integer ticketid);
    @Query(value = "SELECT * from foodorders f WHERE f.showid = :showid", nativeQuery = true)
    List<foodorder> findByShowid(Integer showid);
}
