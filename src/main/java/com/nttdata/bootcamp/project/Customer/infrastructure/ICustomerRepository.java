package com.nttdata.bootcamp.project.Customer.infrastructure;

import com.nttdata.bootcamp.project.Customer.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
