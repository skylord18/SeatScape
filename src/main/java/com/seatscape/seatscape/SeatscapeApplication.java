package com.seatscape.seatscape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
public class SeatscapeApplication {
	public static void main(String[] args) {
		Timestamp ts = Timestamp.valueOf(LocalDateTime.of(2025, 2, 21, 1,2));
		System.out.println(ts);
		SpringApplication.run(SeatscapeApplication.class, args);
	}
}