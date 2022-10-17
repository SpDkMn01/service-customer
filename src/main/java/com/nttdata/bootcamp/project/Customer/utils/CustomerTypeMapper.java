package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import org.springframework.beans.BeanUtils;

public class CustomerTypeMapper implements ICustomerTypeMapper {
    @Override
    public CustomerTypeDto toDto(CustomerType customerType){
        CustomerTypeDto customerTypeDto = new CustomerTypeDto();
        BeanUtils.copyProperties(customerType, customerTypeDto);
        return customerTypeDto;
    }
    @Override
    public CustomerType toEntity(CustomerTypeDto customerTypeDto){
        CustomerType customerType = new CustomerType();
        BeanUtils.copyProperties(customerTypeDto, customerType);
        return customerType;
    }
}
