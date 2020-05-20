package com.qa.service;

import com.qa.domain.Orders;
import com.qa.dto.OrderDTO;
import com.qa.repo.OrdersRepository;
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
class OrdersServiceIntegrationTest {

    @Autowired
    private OrdersService service;

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Orders testOrders;

    private Orders testOrdersWithID;

    private OrderDTO mapToDTO(Orders orders){
        return this.mapper.map(orders, OrderDTO.class);
    }

    @Before
    public void setUp(){
        this.testOrders = new Orders();
        this.repository.deleteAll();
        this.testOrdersWithID = this.repository.save(this.testOrders);
    }

    @Test
    public void readOrdersTest(){
        assertThat(this.service.readOrders())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testOrdersWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createOrderTest(){
        assertEquals(this.mapToDTO(this.testOrdersWithID), this.service.createOrder(testOrders));
    }

    @Test
    public void findOrderByIdTest(){
        assertThat(this.service.findOrderById(this.testOrdersWithID.getOrderId())).isEqualTo(this.mapToDTO(this.testOrdersWithID));
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
    public void deleteOrderTest(){
        assertThat(this.service.deleteOrder(this.testOrdersWithID.getOrderId())).isFalse();
    }

}