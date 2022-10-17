package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.service.ICustomerService;
import com.nttdata.bootcamp.project.Customer.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-customer}")
@RefreshScope
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;
    @GetMapping
    public Flux<CustomerDtoResponse> getCustomer()
    {
        return customerService.getAll();
    }
    @GetMapping(path="/{id}")
    public Mono<CustomerDtoResponse> getCustomerType(@PathVariable String id)
    {
        return customerService.getById(id);
    }
    @PostMapping
    public Mono<CustomerDtoResponse> saveCustomer(@RequestBody Mono<CustomerDtoRequest> customerDtoRequest)
    {
        return customerService.save(customerDtoRequest);
    }
    @PutMapping("/update/{id}")
    public Mono<CustomerDtoResponse> updateCustomer(@RequestBody Mono<CustomerDtoRequest> CustomerDtoMono, @PathVariable String id)
    {
        return customerService.update(CustomerDtoMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id)
    {
        return customerService.delete(id);
    }
}