package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * <h1>CustomerType Mapper</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
@RequiredArgsConstructor
@Component
public class CustomerTypeMapper implements ICustomerTypeMapper {
    @Override
    public CustomerTypeDto toDto(CustomerType customerType) {
        CustomerTypeDto customerTypeDto = new CustomerTypeDto();
        BeanUtils.copyProperties(customerType, customerTypeDto);
        return customerTypeDto;
    }

    @Override
    public CustomerType toEntity(CustomerTypeDto customerTypeDto) {
        CustomerType customerType = new CustomerType();
        BeanUtils.copyProperties(customerTypeDto, customerType);
        return customerType;
    }
}
