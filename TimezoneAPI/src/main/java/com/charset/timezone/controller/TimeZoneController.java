package com.charset.timezone.controller;


import com.charset.timezone.service.TimeZoneService;
import com.charset.timezone.service.TimeZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.charset.timezone.model.TimeZone;

@RestController
@RequestMapping("/time")
public class TimeZoneController {

    @Autowired
    TimeZoneService timeZoneService;

    // Example:  /US:Edmonton, /US:Panama
    @GetMapping(value = "/{timezone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeZone> getTimeZone(@PathVariable("timezone") String timezone) {
        ResponseEntity<TimeZone> entity = null;
        TimeZone timeZoneData = timeZoneService.getTimeZoneData(timezone);
        if (timeZoneData == null && TimeZoneServiceImpl.error.isEmpty())
            entity = new ResponseEntity<>(timeZoneData, HttpStatus.INTERNAL_SERVER_ERROR);
        else if (!TimeZoneServiceImpl.error.isEmpty()) {
            entity = new ResponseEntity<>(new TimeZone(), HttpStatus.BAD_REQUEST);
        } else if (timeZoneData != null) {
            entity = new ResponseEntity<>(timeZoneData, HttpStatus.OK);
        }
        return entity;
    }
}

