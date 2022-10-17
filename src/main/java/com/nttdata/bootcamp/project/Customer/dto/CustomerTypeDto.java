package com.nttdata.bootcamp.project.Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTypeDto {
    private String id;
    private String description;
}
