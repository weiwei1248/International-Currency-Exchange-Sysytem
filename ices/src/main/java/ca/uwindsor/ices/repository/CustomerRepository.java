package ca.uwindsor.ices.repository;

import ca.uwindsor.ices.jpa.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

    Customer findOne(Integer integer);

    Customer save(Customer customer);

}
