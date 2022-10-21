package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Type Controller</h1>
 *
 * @version 1.0
 * @Author Grupo06
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
    @ResponseStatus(HttpStatus.OK)
    public Flux getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono save(@RequestBody Mono<CustomerTypeDto> requestMono) {
        return service.save(requestMono);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono update(@RequestBody Mono<CustomerTypeDto> requestMono, @PathVariable String id) {
        return service.update(requestMono, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
