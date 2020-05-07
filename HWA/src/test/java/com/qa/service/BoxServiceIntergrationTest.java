package com.qa.service;

import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.repo.BoxesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoxServiceIntegrationTest {

    @Autowired
    private BoxService service;

    @Autowired
    private BoxesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Box testBox;

    private Box testBoxWithID;

    private BoxDTO mapToDTO(Box box){
        return this.mapper.map(box, BoxDTO.class);
    }

    @Before
    public void setUp(){
        this.testBox = new Box("Box1");
        this.repository.deleteAll();
        this.testBoxWithID = this.repository.save(this.testBox);
    }

    @Test
    public void readBoxesTest(){
        assertThat(this.service.readBoxes())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testBoxWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createBoxTest(){
        assertEquals(this.mapToDTO(this.testBoxWithID), this.service.createBox(testBox));
    }

    @Test
    public void findBoxByIdTest(){
        assertThat(this.service.findBoxById(this.testBoxWithID.getBoxId())).isEqualTo(this.mapToDTO(this.testBoxWithID));
    }


//    @Test
//    public void updateNoteTest(){
//        Note newNote = new Note("Favourite movies", "The grinch");
//        Note updateNote = new Note(newNote.getTitle(), newNote.getDescription());
//        updateNote.setId(this.testNoteWithID.getId());
//        assertThat(this.service.updateNote(this.testNoteWithID.getId() ,newNote))
//                .isEqualTo(this.mapToDTO(updateNote));
//    }

    @Test
    public void deleteBoxTest(){
        assertThat(this.service.deleteBox(this.testBoxWithID.getBoxId())).isFalse();
    }


}