package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.Customer;

import java.util.function.BiFunction;

public class CreateCustomer implements BiFunction<CustomerTypeDto, CustomerDtoRequest, Customer> {

    @Override
    public Customer apply(CustomerTypeDto customerTypeDto, CustomerDtoRequest customerDtoRequest) {
        Customer customer = new Customer();
        customer.setFirstname(customerDtoRequest.getFirstname());
        customer.setLastname(customerDtoRequest.getLastname());
        customer.setCustomerTypeId(customerDtoRequest.getCustomerTypeId());
        customer.setCustomerType(new CustomerTypeMapper().toEntity(customerTypeDto));
        return customer;
    }
}
