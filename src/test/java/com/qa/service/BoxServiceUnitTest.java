package com.qa.service;

import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.exceptions.BoxNotFoundException;
import com.qa.repo.BoxesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BoxServiceUnitTest {

    @InjectMocks
    private BoxService service;

    @Mock
    private BoxesRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Box> boxList;

    private Box testBox;

    private long boxId = 1L;

    private Box testBoxWithID;

    private BoxDTO boxDTO;

    private BoxDTO mapToDTO(Box box){
        return this.mapper.map(box, BoxDTO.class);
    }

    @Before
    public void setUp() {
        this.boxList = new ArrayList<>();
        this.testBox = new Box("box1");
        this.boxList.add(testBox);
        this.testBoxWithID = new Box(testBox.getBoxName());
        this.testBoxWithID.setBoxId(boxId);
        this.boxDTO = this.mapToDTO(testBoxWithID);
    }

    @Test
    public void getAllBoxesTest(){
        when(repository.findAll()).thenReturn(this.boxList);
        when(this.mapper.map(testBoxWithID, BoxDTO.class)).thenReturn(boxDTO);
        assertFalse("Service returned no Boxes", this.service.readBoxes().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createBoxTest(){
        when(repository.save(testBox)).thenReturn(testBoxWithID);
        when(this.mapper.map(testBoxWithID, BoxDTO.class)).thenReturn(boxDTO);
        assertEquals(this.service.createBox(testBox), this.boxDTO);
        verify(repository, times(1)).save(this.testBox);
    }

    @Test
    public void findBoxByIdTest(){
        when(this.repository.findById(boxId)).thenReturn(java.util.Optional.ofNullable(testBoxWithID));
        when(this.mapper.map(testBoxWithID, BoxDTO.class)).thenReturn(boxDTO);
        assertEquals(this.service.findBoxById(this.boxId), boxDTO);
        verify(repository, times(1)).findById(boxId);
    }

    @Test
    public void deleteBoxByExistingId(){
        when(this.repository.existsById(boxId)).thenReturn(true, false);
        assertFalse(service.deleteBox(boxId));
        verify(repository, times(1)).deleteById(boxId);
        verify(repository, times(2)).existsById(boxId);
    }

    @Test(expected = BoxNotFoundException.class)
    public void deleteBoxByNonExistingId(){
        when(this.repository.existsById(boxId)).thenReturn(false);
        service.deleteBox(boxId);
        verify(repository, times(1)).existsById(boxId);
    }

    @Test
    public void updateBoxTest(){

        Box newBox = new Box("box2");
        Box updateBox = new Box(newBox.getBoxName());
        updateBox.setBoxId(boxId);

        BoxDTO updateBoxDTO = new ModelMapper().map(updateBox, BoxDTO.class);

        when(this.repository.findById(boxId)).thenReturn(java.util.Optional.ofNullable(testBoxWithID));
        when(this.repository.save(updateBox)).thenReturn(updateBox);
        when(this.mapper.map(updateBox, BoxDTO.class)).thenReturn(updateBoxDTO);

        assertEquals(updateBoxDTO, this.service.updateBox(boxId, newBox));
        verify(this.repository, times(1)).findById(boxId);
        verify(this.repository, times(1)).save(updateBox);
    }

}