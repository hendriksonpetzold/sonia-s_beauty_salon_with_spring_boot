package com.example.sonia_beauty_salon.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

import java.time.LocalDateTime;


@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfClient;
    private String nameOfAttendant;
    private String service;
    private Double price;
    private LocalDateTime initialOfBooking;
    private LocalDateTime finishOfBooking;

}
