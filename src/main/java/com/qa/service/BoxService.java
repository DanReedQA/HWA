package com.qa.service;

import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.exceptions.BoxNotFoundException;
import com.qa.repo.BoxesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxService {

    private final BoxesRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public BoxService(BoxesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private BoxDTO mapToDTO(Box box){
        return this.mapper.map(box, BoxDTO.class);
    }

    public List<BoxDTO> readBoxes(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public BoxDTO createBox(Box box){
        return this.mapToDTO(this.repo.save(box));
    }

    public BoxDTO findBoxById(Long boxId){
        return this.mapToDTO(this.repo.findById(boxId).orElseThrow(BoxNotFoundException::new));
    }

    public BoxDTO updateBox(Long boxId, Box box){
        Box update = this.repo.findById(boxId).orElseThrow(BoxNotFoundException::new);
        update.setBoxName(box.getBoxName());
        Box tempBox = this.repo.save(update);
        return this.mapToDTO(tempBox);
    }

    public boolean deleteBox(Long boxId){
        if(!this.repo.existsById(boxId)){
            throw new BoxNotFoundException();
        }
        this.repo.deleteById(boxId);
        return this.repo.existsById(boxId);
    }


}