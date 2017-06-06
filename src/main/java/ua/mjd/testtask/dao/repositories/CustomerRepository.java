package ua.mjd.testtask.dao.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.mjd.testtask.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findByNameAndPhone(String name, String phone);
    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.name = ?1, c.phone = ?2, c.address = ?3 where c.id = ?4")
    void setCustomerInfo(String name, String phone, String address, Long id);
}
