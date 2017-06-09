package ua.mjd.testtask.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.mjd.testtask.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByName(String name);
}
