package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerMapper implements  ICustomerMapper{
    @Override
    public CustomerDtoRequest toDtoRequest(Customer customer) {
        CustomerDtoRequest customerDto = new CustomerDtoRequest();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
    @Override
    public Customer toEntity(CustomerDtoRequest customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }
    @Override
    public CustomerDtoResponse toDtoResponse(Customer customer) {
        CustomerDtoResponse customerDtoResponse = new CustomerDtoResponse();
        BeanUtils.copyProperties(customer, customerDtoResponse);
        customerDtoResponse.setCustomerTypeUrl("/api/v1/customerTypes/" + customer.getCustomerTypeId());
        return customerDtoResponse;
    }
}