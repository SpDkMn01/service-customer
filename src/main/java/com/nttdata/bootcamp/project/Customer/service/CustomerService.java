package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerRepository;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.CustomerMapper;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Service</h1>
 * Esta clase tiene la finalidad de concentrar la logica necesaria para el CRUD de los objetos Customers
 * @author Grupo05
 * @version 1.0
 * @since 2022-10-14
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService<CustomerDtoRequest,CustomerDtoResponse>{
    @Autowired
    private final ICustomerRepository customerRepository;
    @Value("${message.uri}")
    String uri;
    @Override
    public Flux<CustomerDtoResponse> getAll()
    {
        ICustomerMapper mapper = new CustomerMapper(uri);
        return customerRepository.findAll()
                .map(mapper::toDtoResponse);
    }
    @Override
    public Mono<CustomerDtoResponse> getById(String id)
    {
        ICustomerMapper mapper = new CustomerMapper(uri);
        return customerRepository.findById(id)
                .map(mapper::toDtoResponse);
    }
    @Override
    public Mono<CustomerDtoResponse> save(Mono<CustomerDtoRequest> object)
    {
        ICustomerMapper mapper = new CustomerMapper(uri);
        return object.map(mapper::toEntity)
                .flatMap(customerRepository::insert)
                .map(mapper::toDtoResponse);
    }
    @Override
    public Mono<CustomerDtoResponse> update(Mono<CustomerDtoRequest> object, String id)
    {
        ICustomerMapper mapper = new CustomerMapper(uri);
        return customerRepository.findById(id)
                .flatMap(
                        p -> object.map(mapper::toEntity)
                                .doOnNext(e -> e.setId(id))
                )
                .flatMap(customerRepository::save)
                .map(mapper::toDtoResponse);
    }
    @Override
    public Mono<Void> delete(String id)
    {
        return customerRepository.deleteById(id);
    }
}