package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.Customer;

public interface ICustomerMapper {
    CustomerDtoRequest toDtoRequest(Customer customer);
    Customer toEntity(CustomerDtoRequest customerDto);
    CustomerDtoResponse toDtoResponse(Customer customer);
}
