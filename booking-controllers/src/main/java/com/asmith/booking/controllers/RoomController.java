package com.asmith.booking.controllers;

import com.asmith.booking.entities.Room;
import com.asmith.booking.domain.beans.DomainBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asmith
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    DomainBean<Room> roomBean;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Room> create(@RequestBody Room room) {
        // create room
        return new ResponseEntity<>(roomBean.create(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Room> read(@PathVariable("id") String id) {
        // return single room
        return new ResponseEntity<>(roomBean.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<Room>> readAll() {

        return new ResponseEntity<>(roomBean.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<Room> update(@PathVariable("id") String id, @RequestBody Room room) {
        // get the room and replace it with the incoming object
        return new ResponseEntity<>(roomBean.update(id, room), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    ResponseEntity<Room> patch(@PathVariable("id") String id, @RequestBody Room room) {
        // get the room and patch just the field that isn't null
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    void delete(@PathVariable("id") String id) {
        roomBean.delete(id);
    }

}
