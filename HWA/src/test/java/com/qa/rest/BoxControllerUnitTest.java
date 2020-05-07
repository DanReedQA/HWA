package com.qa.rest;

import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.service.BoxService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoxControllerUnitTest {

    @InjectMocks
    private BoxController boxController;

    @Mock
    private BoxService service;

    private List<Box> boxes;

    private Box testBox;

    private Box testBoxWitId;

    private long boxId = 1L;

    private BoxDTO boxDTO;

    private final ModelMapper mapper = new ModelMapper();

    private BoxDTO mapToDTO(Box box){
        return this.mapper.map(box, BoxDTO.class);
    }

    @Before
    public void setUp(){
        this.boxes = new ArrayList<>();
        this.testBox = new Box("Box1");
        this.boxes.add(testBox);
        this.testBoxWitId = new Box(testBox.getBoxName());
        this.testBoxWitId.setBoxId(this.boxId);
        this.boxDTO = this.mapToDTO(testBoxWitId);
    }

    @Test
    public void getAllBoxesTest(){
        when(service.readBoxes()).thenReturn(this.boxes.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No boxes found", this.boxController.getAllBoxes().getBody().isEmpty());
        verify(service, times(1)).readBoxes();
    }

    @Test
    public void createBoxTest(){
        when(this.service.createBox(testBox)).thenReturn(this.boxDTO);
        assertEquals(this.boxController.createBox(testBox), new ResponseEntity<BoxDTO>(this.boxDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createBox(testBox);
    }

    @Test
    public void deleteBoxTestFalse(){
        this.boxController.deleteBox(boxId);
        verify(service, times(1)).deleteBox(boxId);
    }


    @Test
    public void deleteBoxTestTrue(){
        when(service.deleteBox(3L)).thenReturn(true);
        this.boxController.deleteBox(3L);
        verify(service, times(1)).deleteBox(3L);
    }

    @Test
    public void getBoxByIDTest(){
        when(this.service.findBoxById(boxId)).thenReturn(this.boxDTO);
        assertEquals(this.boxController.getBoxById(boxId), new ResponseEntity<BoxDTO>(this.boxDTO, HttpStatus.OK));
        verify(service, times(1)).findBoxById(boxId);
    }

}