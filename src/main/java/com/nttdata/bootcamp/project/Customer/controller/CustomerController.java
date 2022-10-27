package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.service.ICustomerService;
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
 * <h1>Customer Controller</h1>
 *
 * @version 1.0
 * @Author Grupo06
 * @since 2022-10-18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-customer}")
@RefreshScope
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Produces(MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerDtoResponse> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<CustomerDtoResponse>> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDtoResponse>> save(@RequestBody Mono<CustomerDtoRequest> requestMono) {
        return service.save(requestMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<CustomerDtoResponse>> update(@RequestBody Mono<CustomerDtoRequest> requestMono, @PathVariable String id) {
        return service.update(requestMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<CustomerDtoResponse>> delete(@PathVariable String id) {
        return service.delete(id);
    }
}