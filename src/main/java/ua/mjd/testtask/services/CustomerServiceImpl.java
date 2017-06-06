package ua.mjd.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mjd.testtask.dao.repositories.CustomerRepository;
import ua.mjd.testtask.exceptions.SuchCustomerAlreadyExist;
import ua.mjd.testtask.exceptions.UnexpectedNullException;
import ua.mjd.testtask.model.Customer;
import ua.mjd.testtask.utils.Checker;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customers;
    private Checker checker;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customers, Checker checker) {
        this.customers = customers;
        this.checker = checker;
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
    public Customer getCustomerById(long id) throws UnexpectedNullException {
        Customer result = customers.findOne(id);
        checker.checkNull(result, String.format("Customer with id %d not found!", id));
        return result;
    }

    @Override
    public Customer getCustomerByName(String name) throws UnexpectedNullException {
        Customer result = customers.findByName(name);
        checker.checkNull(result, String.format("Customer with name %s not found!", name));
        return result;
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) throws UnexpectedNullException{
        Customer oldCustomer = customers.findOne(id);
        checker.checkNull(oldCustomer,String.format("Customer with id %d not found!", id));
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
