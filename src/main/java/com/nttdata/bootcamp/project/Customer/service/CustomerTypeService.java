package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.CustomerTypeMapper;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Type Service</h1>
 * Esta clase tiene la finalidad de concentrar la logica necesaria para el CRUD de los objetos CustomerType
 * @author Grupo05
 * @version 1.0
 * @since 2022-10-14
 */
@Service
@RequiredArgsConstructor
public class CustomerTypeService implements ICustomerTypeService<CustomerTypeDto,CustomerTypeDto> {
    @Autowired
    private final ICustomerTypeRepository repository;
    @Override
    public Flux<CustomerTypeDto> getAll()
    {
        ICustomerTypeMapper mapper = new CustomerTypeMapper();
        return repository.findAll()
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> getById(String id)
    {
        ICustomerTypeMapper mapper = new CustomerTypeMapper();
        return repository.findById(id)
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> save(Mono<CustomerTypeDto> object)
    {
        ICustomerTypeMapper mapper = new CustomerTypeMapper();
        return object.map(mapper::toEntity)
                .flatMap(repository::insert)
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> update(Mono<CustomerTypeDto> object, String id)
    {
        ICustomerTypeMapper mapper = new CustomerTypeMapper();
        return repository.findById(id)
                .flatMap(
                        p -> object.map(mapper::toEntity)
                        .doOnNext(e -> e.setId(id))
                )
                .flatMap(repository::save)
                .map(mapper::toDto);
    }
    @Override
    public Mono<Void> delete(String id)
    {
        return repository.deleteById(id);
    }
}