package com.qa.rest;

import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BoxDTO>> getAllBoxes(){
        return ResponseEntity.ok(this.service.readBoxes());
    }

    @PostMapping("/createBox")
    public ResponseEntity<BoxDTO> createBox(@RequestBody Box card){
        return new ResponseEntity<BoxDTO>(this.service.createBox(card), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBox/{boxId}")
    public ResponseEntity<?> deleteBox(@PathVariable Long boxId){
        return this.service.deleteBox(boxId)
        ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        : ResponseEntity.noContent().build();
    }

    @GetMapping("/getBoxById/{boxId}")
    public ResponseEntity<BoxDTO> getBoxById(@PathVariable Long boxId){
        return ResponseEntity.ok(this.service.findBoxById(boxId));
    }

    @PutMapping("/updateBox/{boxId}")
    public ResponseEntity<BoxDTO> updateBox(@PathVariable Long boxId, @RequestBody Box box){
        return ResponseEntity.ok(this.service.updateBox(boxId, box));
    }

}
