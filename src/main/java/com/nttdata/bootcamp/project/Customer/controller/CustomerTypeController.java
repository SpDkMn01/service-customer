package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoResponse;
import com.nttdata.bootcamp.project.Customer.service.ICustomerTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.Produces;

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
    @Produces(MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> save(@RequestBody Mono<CustomerTypeDtoRequest> requestMono) {
        return service.save(requestMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> update(@RequestBody Mono<CustomerTypeDtoRequest> requestMono, @PathVariable String id) {
        return service.update(requestMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> delete(@PathVariable String id) {
        return service.delete(id);
    }
}