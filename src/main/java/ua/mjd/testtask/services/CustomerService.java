package ua.mjd.testtask.services;

import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.exceptions.SuchCustomerDoesNotExist;
import ua.mjd.testtask.exceptions.UnexpectedNullException;
import ua.mjd.testtask.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer createCustomer(Customer customer) throws SuchCustomerAlreadyExist;

    Customer getCustomerById(long id) throws SuchCustomerDoesNotExist;

    Customer getCustomerByName(String name) throws SuchCustomerDoesNotExist;

    Customer updateCustomer(long id, Customer customer) throws SuchCustomerDoesNotExist;
}
