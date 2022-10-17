package com.nttdata.bootcamp.project.Customer.infrastructure;

import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
}
