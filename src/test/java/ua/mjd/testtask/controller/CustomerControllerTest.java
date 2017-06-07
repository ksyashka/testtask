package ua.mjd.testtask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.mjd.testtask.model.Customer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Test
    public void testGetPositive() throws Exception {
        createCustomer();
        mockMvc.perform(get("/customers/get?id=1")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.phone").value("TestPhone"))
                .andExpect(jsonPath("$.address").value("TestAddress"));
    }


    private void createCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setName("TestName");
        customer.setPhone("TestPhone");
        customer.setAddress("TestAddress");

        String name = "someCourse";
        mockMvc.perform(post("/courses/add")
                .andExpect(status().isOk());
    }

}
