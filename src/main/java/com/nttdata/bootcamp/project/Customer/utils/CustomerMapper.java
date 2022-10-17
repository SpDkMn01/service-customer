package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
public class CustomerMapper implements  ICustomerMapper{
    private String uri;
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
        customerDtoResponse.setCustomerTypeUrl(uri + customer.getCustomerTypeId());
        return customerDtoResponse;
    }
}