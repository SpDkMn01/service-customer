package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;

/**
 * <h1>ICustomerType Mapper</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
public interface ICustomerTypeMapper {
    CustomerType toEntity(CustomerTypeDtoRequest customerTypeDtoRequest);

    CustomerType toEntity(CustomerTypeDtoResponse customerTypeDtoResponse);

    CustomerTypeDtoResponse toDtoResponse(CustomerType customerType);

}
