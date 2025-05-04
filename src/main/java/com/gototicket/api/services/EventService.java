package com.gototicket.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.gototicket.api.domain.event.Event;
import com.gototicket.api.domain.event.EventRequestDTO;
import com.gototicket.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3Object;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO data){
        String imgUrl=null;

        if(data.image() != null){
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();

        //can be better code
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        //Saving on DB
        eventRepository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile){
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try{
            File file = this.convertMultipartToFile(multipartFile);
            s3Object.putObject(bucketName, fileName, file);
            file.delete();
            return s3Object.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile;
    }
}
