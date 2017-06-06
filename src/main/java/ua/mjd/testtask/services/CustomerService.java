package ua.mjd.testtask.services;

import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.exceptions.UnexpectedNullException;
import ua.mjd.testtask.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer createCustomer(Customer customer) throws SuchCustomerAlreadyExist;

    Customer getCustomerById(long id) throws UnexpectedNullException;

    Customer getCustomerByName(String name) throws UnexpectedNullException;

    Customer updateCustomer(long id, Customer customer) throws UnexpectedNullException;
}
