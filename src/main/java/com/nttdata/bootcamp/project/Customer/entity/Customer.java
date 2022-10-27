package com.nttdata.bootcamp.project.Customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <h1>Customer Entity</h1>
 * Esta clase representa la estructura de la colección "customer"
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String customerTypeId;
    private CustomerType customerType;
}
