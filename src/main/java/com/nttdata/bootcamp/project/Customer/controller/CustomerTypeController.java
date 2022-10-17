package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.service.CustomerTypeService;
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
@RequestMapping("${message.path-customerType}")
@RefreshScope
public class CustomerTypeController {
    @Autowired
    private CustomerTypeService service;
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
    public Mono<CustomerTypeDto> save(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono)
    {
        return service.save(customerTypeDtoMono);
    }
    @PutMapping("/update/{id}")
    public Mono<CustomerTypeDto> update(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono, @PathVariable String id)
    {
        return service.update(customerTypeDtoMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id)
    {
        return service.delete(id);
    }
}
