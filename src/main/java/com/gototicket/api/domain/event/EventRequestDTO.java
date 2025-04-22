package com.gototicket.api.domain.event;


import org.springframework.web.multipart.MultipartFile;

// what is a record, how to use DTO correctly?
public record EventRequestDTO(String title, String description, Long date, String city, String state, Boolean remote, String eventUrl, MultipartFile image) {

}
