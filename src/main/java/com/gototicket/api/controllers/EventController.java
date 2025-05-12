package com.gototicket.api.controllers;

import com.gototicket.api.domain.event.Event;
import com.gototicket.api.domain.event.EventRequestDTO;
import com.gototicket.api.domain.event.EventResponseDTO;
import com.gototicket.api.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> events = this.eventService.getEvents(page, size);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> events = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> getFilteredEvents(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(required = false) String city,
                                                                    @RequestParam(required = false) String state,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<EventResponseDTO> events = eventService.getFilteredEvents(page, size, city, state, startDate, endDate);
        return ResponseEntity.ok(events);
    }
}
