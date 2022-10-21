package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.Customer;

/**
 * <h1>ICustomer Mapper</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
public interface ICustomerMapper {
    CustomerDtoRequest toDtoRequest(Customer customer);

    Customer toEntity(CustomerDtoRequest customerDto);

    CustomerDtoResponse toDtoResponse(Customer customer);
}
