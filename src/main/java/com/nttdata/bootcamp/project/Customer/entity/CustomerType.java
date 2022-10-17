package com.nttdata.bootcamp.project.Customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <h1>CustomerType Entity</h1>
 * Esta clase representa la estructura de la colección "customerTypes"
 * @Author Grupo05
 * @version 1.0
 * @since 2022-10-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customerTypes")
public class CustomerType {
    @Id
    private String id;
    private String description;
}
