package com.gototicket.api.controllers;

import com.gototicket.api.domain.event.Event;
import com.gototicket.api.domain.event.EventRequestDTO;
import com.gototicket.api.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam String title,
                                         @RequestParam String description,
                                         @RequestParam String city,
                                         @RequestParam Long date,
                                         @RequestParam String state,
                                         @RequestParam boolean remote,
                                         @RequestParam String eventUrl,
                                         @RequestParam MultipartFile image
    ){
        EventRequestDTO eventRequestDto = new EventRequestDTO(
                title,
                description,
                date,
                city,
                state,
                remote,
                eventUrl,
                image
        );
        Event newEvent = this.eventService.createEvent(eventRequestDto);
        return ResponseEntity.ok(newEvent);
    }

}
