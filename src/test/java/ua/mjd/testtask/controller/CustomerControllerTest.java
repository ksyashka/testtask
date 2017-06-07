package ua.mjd.testtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.mjd.testtask.model.Customer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetPositive() throws Exception {
        createCustomer();
        mockMvc.perform(get("/customers/get?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.phone").value("TestPhone"))
                .andExpect(jsonPath("$.address").value("TestAddress"));
    }

    @Test
    public void testGetNegative() {
        try {
            mockMvc.perform(get("/customers/get?id=2"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertThat(e.getMessage(), containsString("Customer with id 2 not found!"));
        }
    }

    private void createCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setName("TestName");
        customer.setPhone("TestPhone");
        customer.setAddress("TestAddress");

        mockMvc.perform(post("/customers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
