package com.gototicket.api.domain.address;

import com.gototicket.api.domain.event.Event;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class Address{

    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;



}
