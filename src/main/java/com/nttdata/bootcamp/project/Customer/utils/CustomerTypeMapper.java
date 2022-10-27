package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoResponse;
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
    public CustomerType toEntity(CustomerTypeDtoRequest customerTypeDtoRequest) {
        CustomerType customerType = new CustomerType();
        BeanUtils.copyProperties(customerTypeDtoRequest, customerType);
        return customerType;
    }

    @Override
    public CustomerType toEntity(CustomerTypeDtoResponse customerTypeDtoResponse) {
        CustomerType customerType = new CustomerType();
        BeanUtils.copyProperties(customerTypeDtoResponse, customerType);
        return customerType;
    }

    @Override
    public CustomerTypeDtoResponse toDtoResponse(CustomerType customerType) {
        CustomerTypeDtoResponse customerTypeDtoResponse = new CustomerTypeDtoResponse();
        BeanUtils.copyProperties(customerType, customerTypeDtoResponse);
        return customerTypeDtoResponse;
    }
}
