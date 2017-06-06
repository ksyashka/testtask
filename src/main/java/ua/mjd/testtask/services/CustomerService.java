package ua.mjd.testtask.services;

import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Customer createCustomer(Customer customer) throws SuchCustomerAlreadyExist;

    Customer getCustomerById(long id);

    Customer getCustomerByName(String name);

    Customer updateCustomer(long id, Customer customer);
}
