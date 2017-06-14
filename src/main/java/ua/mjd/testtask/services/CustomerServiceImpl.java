package ua.mjd.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mjd.testtask.dao.repositories.CustomerRepository;
import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.exceptions.SuchCustomerDoesNotExist;
import ua.mjd.testtask.exceptions.UnexpectedNullException;
import ua.mjd.testtask.model.Customer;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customers;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customers) {
        this.customers = customers;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customers.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) throws SuchCustomerAlreadyExist {
        if (customers.findByNameAndPhone(customer.getName(), customer.getPhone()) != null)
            throw new SuchCustomerAlreadyExist("Such customer already exist!");
        return customers.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) throws SuchCustomerDoesNotExist {
        Customer result = customers.findOne(id);
        Object object = new Object();
        if (Objects.isNull(result))
            throw new SuchCustomerDoesNotExist(String.format("Customer with id %d not found!", id));
        return result;
    }

    @Override
    public Customer getCustomerByName(String name) throws SuchCustomerDoesNotExist {
        Customer result = customers.findByName(name);
        if (Objects.isNull(result))
            throw new SuchCustomerDoesNotExist(String.format("Customer with name %s not found!", name));
        return result;
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) throws SuchCustomerDoesNotExist {
        Customer oldCustomer = customers.findOne(id);
        if (Objects.isNull(oldCustomer))
            throw new SuchCustomerDoesNotExist(String.format("Customer with id %d not found!", id));

        String name = customer.getName();
        String phone = customer.getPhone();
        String address = customer.getAddress();

        if (name == null) name = oldCustomer.getName();
        if (phone == null) phone = oldCustomer.getPhone();
        if (address == null) address = oldCustomer.getAddress();

        customers.setCustomerInfo(name, phone, address, id);
        return customers.findOne(id);
    }
}
