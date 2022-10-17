package com.nttdata.bootcamp.project.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>CustomerDto</h1>
 * Esta clase representa el objeto de valor para los registro de Customer.
 * @author Grupo05
 * @version 1.0
 * @since 2022-10-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoRequest {
    private String firstname;
    private String lastname;
    private String customerTypeId;
}
