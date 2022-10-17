package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;

public interface ICustomerTypeMapper {
    CustomerTypeDto toDto(CustomerType customerType);
    CustomerType toEntity(CustomerTypeDto customerTypeDto);
}
