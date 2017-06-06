package ua.mjd.testtask.services;

import ua.mjd.testtask.dao.repositories.CustomerRepository;
import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customers;

    @Override
    public List<Customer> getAllCustomer() {
        return (List<Customer>)customers.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) throws SuchCustomerAlreadyExist {
        if (customers.findByNameAndPhone(customer.getName(),customer.getPhone())!=null)
            throw new SuchCustomerAlreadyExist("Such customer already exist!");
        return customers.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        return null;
    }

    @Override
    public Customer getCustomerByName(String name) {
        return null;
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        return null;
    }
}
