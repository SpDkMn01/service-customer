package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * <h1>Customer Type Controller</h1>
 * @Author Grupo06
 * @version 1.0
 * @since 2022-10-18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-customerType}")
@RefreshScope
public class CustomerTypeController {
    @Autowired
    private ICustomerTypeService service;
    @GetMapping
    public Flux<CustomerTypeDto> getAll()
    {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Mono<CustomerTypeDto> getById(@PathVariable String id)
    {
        return service.getById(id);
    }
    @PostMapping
    public Mono<CustomerTypeDto> save(@RequestBody Mono<CustomerTypeDto> requestMono)
    {
        return service.save(requestMono);
    }
    @PutMapping("/update/{id}")
    public Mono<CustomerTypeDto> update(@RequestBody Mono<CustomerTypeDto> requestMono, @PathVariable String id)
    {
        return service.update(requestMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id)
    {
        return service.delete(id);
    }
}
