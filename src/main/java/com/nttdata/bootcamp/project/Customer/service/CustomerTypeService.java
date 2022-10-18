package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Type Service</h1>
 * Esta clase tiene la finalidad de concentrar la logica necesaria para el CRUD de los objetos CustomerType
 * @author Grupo06
 * @version 1.0
 * @since 2022-10-14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerTypeService implements ICustomerTypeService<CustomerTypeDto,CustomerTypeDto> {
    @Autowired
    private final ICustomerTypeRepository repository;
    @Autowired
    private final ICustomerTypeMapper mapper;
    @Override
    public Flux<CustomerTypeDto> getAll()
    {
        return repository.findAll()
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> getById(String id)
    {
        return repository.findById(id)
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> save(Mono<CustomerTypeDto> object)
    {
        return object.map(mapper::toEntity)
                .flatMap(repository::insert)
                .map(mapper::toDto);
    }
    @Override
    public Mono<CustomerTypeDto> update(Mono<CustomerTypeDto> object, String id)
    {
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