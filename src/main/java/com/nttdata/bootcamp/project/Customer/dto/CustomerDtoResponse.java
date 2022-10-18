package com.nttdata.bootcamp.project.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * <h1>CustomerDtoResponse</h1>
 * @Author Grupo06
 * @version 1.0
 * @since 2022-10-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String customerTypeUrl;
}
