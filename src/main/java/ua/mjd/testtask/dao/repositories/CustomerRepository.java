package ua.mjd.testtask.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.mjd.testtask.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findByNameAndPhone(String name, String phone);
}
