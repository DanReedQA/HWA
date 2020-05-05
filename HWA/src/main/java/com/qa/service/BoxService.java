package com.qa.service;

import com.qa.domain.Box;
import com.qa.exceptions.BoxNotFoundException;
import com.qa.repo.BoxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    private final BoxesRepository repo;

    @Autowired
    public BoxService(BoxesRepository repo) {
        this.repo = repo;
    }

    public List<Box> readBoxes(){
        return this.repo.findAll();
    }

    public Box createBox(Box box){
        return this.repo.save(box);
    }

    public Box findBoxById(Long boxId){
        return this.repo.findById(boxId).orElseThrow(BoxNotFoundException::new);
    }

    public Box updateBox(Long boxId, Box box){
        Box update = findBoxById(boxId);
        update.setBoxName(box.getBoxName());
        return this.repo.save(update);
    }

    public boolean deleteBox(Long boxId){
        if(!this.repo.existsById(boxId)){
            throw new BoxNotFoundException();
        }
        this.repo.deleteById(boxId);
        return this.repo.existsById(boxId);
    }


}