package com.nttdata.bootcamp.project.Customer.infrastructure;

import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
/**
 * <h1>ICustomerType Repository</h1>
 * @Author Grupo06
 * @version 1.0
 * @since 2022-10-18
 */
@Repository
public interface ICustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
}
