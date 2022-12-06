package com.example.calendar.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime initialOfBooking;
    private LocalDateTime finishOfBooking;

}
