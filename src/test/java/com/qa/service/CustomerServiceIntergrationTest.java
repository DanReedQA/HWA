package com.qa.service;

import com.qa.domain.Customer;
import com.qa.dto.CustomerDTO;
import com.qa.repo.CustomersRepository;
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
class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomersRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Customer testCustomer;

    private Customer testCustomerWithID;

    private CustomerDTO mapToDTO(Customer customer){
        return this.mapper.map(customer, CustomerDTO.class);
    }

    @Before
    public void setUp(){
        this.testCustomer = new Customer("Dan");
        this.repository.deleteAll();
        this.testCustomerWithID = this.repository.save(this.testCustomer);
    }

    @Test
    public void readCustomersTest(){
        assertThat(this.service.readCustomers())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testCustomerWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createCustomerTest(){
        assertEquals(this.mapToDTO(this.testCustomerWithID), this.service.createCustomer(testCustomer));
    }

    @Test
    public void findCustomerByIdTest(){
        assertThat(this.service.findCustomerById(this.testCustomerWithID.getCustomerId())).isEqualTo(this.mapToDTO(this.testCustomerWithID));
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
    public void deleteCustomerTest(){
        assertThat(this.service.deleteCustomer(this.testCustomerWithID.getCustomerId())).isFalse();
    }
}