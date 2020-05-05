package com.qa.rest;

import com.qa.domain.Box;
import com.qa.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class BoxController {

    private final BoxService service;

    @Autowired
    public BoxController(BoxService service) {
        this.service = service;
    }

    @GetMapping("/getAllBoxes")
    public List<Box> getAllBoxes(){
        return this.service.readBoxes();
    }

    @PostMapping("/createBox")
    public Box createBox(@RequestBody Box box){
        return this.service.createBox(box);
    }

    @DeleteMapping("/deleteBox/{boxId}")
    public boolean deleteBox(@PathVariable Long boxId){
        return this.service.deleteBox(boxId);
    }

    @GetMapping("/getBoxById/{boxId}")
    public Box getBoxById(@PathVariable Long boxId){
        return this.service.findBoxById(boxId);
    }

    @PutMapping("/updateBox/{boxId}")
    public Box updateBox(@PathVariable Long boxId, @RequestBody Box box){ return this.service.updateBox(boxId, box); }

    @PutMapping("/updateBox2")
    public Box updateCard2(@PathParam("boxId") Long boxId, @RequestBody Box box){ return this.service.updateBox(boxId, box); }

}
