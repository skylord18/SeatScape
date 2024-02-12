package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.fooditem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface fooditemDAO extends JpaRepository<fooditem, Integer> {
    @Query(value = "SELECT * from fooditems f where f.itemtype = :itemtype", nativeQuery = true)
    List<fooditem> findByitemtype(String itemtype);
}
