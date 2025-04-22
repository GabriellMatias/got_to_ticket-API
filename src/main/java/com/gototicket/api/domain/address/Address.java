package com.gototicket.api.domain.address;

import com.gototicket.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "address")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
