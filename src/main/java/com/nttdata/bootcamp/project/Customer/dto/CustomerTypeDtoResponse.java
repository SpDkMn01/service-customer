package com.nttdata.bootcamp.project.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>CustomerTypeDto</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTypeDtoResponse {
    private String id;
    private String description;
}
