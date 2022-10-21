package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;

/**
 * <h1>ICustomerType Mapper</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
public interface ICustomerTypeMapper {
    CustomerTypeDto toDto(CustomerType customerType);

    CustomerType toEntity(CustomerTypeDto customerTypeDto);
}
