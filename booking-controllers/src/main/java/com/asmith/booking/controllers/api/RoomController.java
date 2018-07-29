package com.asmith.booking.controllers.api;

import com.asmith.booking.entities.Room;
import com.asmith.booking.services.RoomDomainBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asmith
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomDomainBean roomBean;

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    ResponseEntity<Room> next(@RequestParam("type") String type) {
        return new ResponseEntity<>(roomBean.next(type), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Room> read(@PathVariable("id") String id) {
        return new ResponseEntity<>(roomBean.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    ResponseEntity<List<Room>> available() {
        return new ResponseEntity<>(roomBean.findAvailableRooms(), HttpStatus.OK);
    }

    @RequestMapping(value = "/availableTypes", method = RequestMethod.GET)
    ResponseEntity<List<String>> availableTypes() {
        return new ResponseEntity<>(roomBean.findAvailableRoomTypes(), HttpStatus.OK);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    ResponseEntity<List<String>> types() {
        return new ResponseEntity<>(roomBean.findAllRoomTypes(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Room>> readAll() {
        return new ResponseEntity<>(roomBean.findAll(), HttpStatus.OK);
    }

}
