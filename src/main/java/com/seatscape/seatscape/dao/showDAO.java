package com.seatscape.seatscape.dao;

import com.seatscape.seatscape.model.show;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface showDAO extends JpaRepository<show, Integer> {
    @Query(value = "SELECT * FROM show s where s.cinemaid = :cinemaid", nativeQuery = true)
    List<show> findBycinemaid(Integer cinemaid);
    @Query(value = "SELECT * FROM show s where s.movieid = :movieid", nativeQuery = true)
    List<show> getbymovieid(Integer movieid);
    @Query(value = "SELECT * FROM show s where s.starttime > :timestamp AND s.cinemaid = :cinemaid", nativeQuery = true)
    List<show> getfutureshowsbycinemaid(Timestamp timestamp, Integer cinemaid);
    @Query(value = "SELECT * FROM show s where s.starttime > :timestamp", nativeQuery = true)
    List<show> getfutureshows(Timestamp timestamp);
    @Query(value = "SELECT * FROM show s where s.starttime > :timestamp AND s.movieid = :movieid", nativeQuery = true)
    List<show> getfutureshowsbymovieid(Timestamp timestamp, Integer movieid);
    @Query(value = "SELECT s.* FROM show s, cinema c WHERE c.city = :cityname AND s.cinemaid = c.cinemaid", nativeQuery = true)
    List<show> getShowsByCityName(@Param("cityname") String cityname);
    @Query(value = "SELECT s.* " +
            "FROM show s " +
            "JOIN cinema c ON s.cinemaid = c.cinemaid " +
            "JOIN movie m ON s.movieid = m.movieid " +
            "WHERE c.city = :cityname AND m.title = :moviename",
            nativeQuery = true)
    List<show> getShowsByCityAndMovieName(@Param("cityname") String cityname, @Param("moviename") String moviename);
    @Query(value = "SELECT availableseats FROM show s WHERE showid = :showid", nativeQuery = true)
    Integer getAvailablesetsfromshowid(@Param("showid") Integer showid);
    @Modifying
    @Query(value = "UPDATE show  SET availableseats = :finalvalue WHERE showid = :showid", nativeQuery = true)
    void reduceseatsbycount(@Param("showid") Integer showid, @Param("finalvalue") Integer finalvalue);
}
