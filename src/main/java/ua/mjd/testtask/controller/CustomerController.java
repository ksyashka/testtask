package ua.mjd.testtask.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.mjd.testtask.exceptions.AppException;
import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.model.Customer;
import ua.mjd.testtask.services.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(httpMethod = "GET",
            value = "Resource to get a customer by id",
            response = Customer.class,
            produces = "application/json")
    @RequestMapping(value = "/customers/get", method = RequestMethod.GET)
    public Customer getCustomerByID(@RequestParam int id) throws AppException {

        Customer customer = customerService.getCustomerById(id);
        LOGGER.info("Customer get - OK, id {}", id);
        return customer;
    }

    @ApiOperation(httpMethod = "GET",
            value = "Resource to get a customer by name",
            response = Customer.class,
            produces = "application/json")
    @RequestMapping(value = "/customers/getByName", method = RequestMethod.GET)
    public Customer getCustomerByName(@RequestParam String name) throws AppException {

        Customer customer = customerService.getCustomerByName(name);
        LOGGER.info("Customer get - OK, name {}", name);
        return customer;
    }

    @ApiOperation(httpMethod = "GET",
            value = "Resource to get all customers",
            response = List.class,
            produces = "application/json")
    @RequestMapping(value = "/customers/getAll", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() throws AppException {

        List<Customer> courses = customerService.getAllCustomers();
        LOGGER.info("Course get all - OK");
        return courses;
    }

    @ApiOperation(httpMethod = "POST",
            value = "Resource to create a customere",
            response = String.class,
            produces = "application/json")
    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String createCustomer(@RequestBody @Valid Customer customer, HttpServletRequest request) throws SuchCustomerAlreadyExist {
        Customer result = customerService.createCustomer(customer);

        LOGGER.info("Customer CREATE - OK. Customer (name - {}, phone - {}, address - {})",
                result.getName(),
                result.getPhone(),
                result.getAddress());
        return "Customer CREATE - OK";
    }

    @ApiOperation(httpMethod = "POST",
            value = "Resource to create a customer",
            response = String.class,
            produces = "application/json")
    @RequestMapping(value = "/customers/update", method = RequestMethod.POST)
    public String updateCustomer(@RequestParam int id,
                                 @RequestBody @Valid Customer customer, HttpServletRequest request) throws AppException {
        Customer result = customerService.updateCustomer(id, customer);

        LOGGER.info("Customer UPADTE - OK. Customer (name - {}, phone - {}, address - {})",
                result.getName(),
                result.getPhone(),
                result.getAddress());
        return "Customer UPDATE - OK";
    }
}
