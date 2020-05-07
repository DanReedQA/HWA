package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Box;
import com.qa.dto.BoxDTO;
import com.qa.repo.BoxesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoxControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private BoxesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Box testBox;

    private Box testBoxWithID;

    private long boxId;

    private BoxDTO boxDTO;

    private BoxDTO mapToDTO(Box box){
        return this.mapper.map(box, BoxDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testBox = new Box("Box1");
        this.testBoxWithID = this.repository.save(testBox);
        this.boxId = testBoxWithID.getBoxId();
        this.boxDTO = this.mapToDTO(testBoxWithID);
    }

    @Test
    public void getAllBoxesTest() throws Exception {
        List<BoxDTO> boxDTOList = new ArrayList<>();
        boxDTOList.add(boxDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllBoxes")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(boxDTOList));
    }

    @Test
    public void getBoxByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getBoxById/" + this.boxId)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(boxDTO));
    }

    @Test
    public void createBoxTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createBox")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testBox))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(boxDTO));
    }

    @Test
    public void deleteBoxTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteBox/" + this.boxId)
        ).andExpect(status().isNoContent());
    }


}