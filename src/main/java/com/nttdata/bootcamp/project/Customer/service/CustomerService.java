package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerRepository;
import com.nttdata.bootcamp.project.Customer.utils.CreateCustomer;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Service</h1>
 * Esta clase tiene la finalidad de concentrar la logica necesaria para el CRUD de los objetos Customers
 *
 * @author Grupo06
 * @version 1.0
 * @since 2022-10-14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService<CustomerDtoRequest, CustomerDtoResponse> {
    @Autowired
    private final ICustomerRepository repository;
    @Autowired
    private final ICustomerTypeService service;
    @Autowired
    private final ICustomerMapper mapper;
    @Value("${message.uri}")
    String uri;

    @Override
    public Flux<CustomerDtoResponse> getAll() {
        return repository.findAll()
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<CustomerDtoResponse> getById(String id) {
        return repository.findById(id)
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<CustomerDtoResponse> save(Mono<CustomerDtoRequest> object) {
        return object.flatMap(
                (request) ->
                {
                    // Llamo a un consumer y devuelvo el Mono de Tipo a usar
                    Mono<CustomerTypeDto> customerTypeDtoMono = service.getById(request.getCustomerTypeId());
                    // Uso el Mono de tipo y lo convierto en Mono Customer
                    return customerTypeDtoMono.map((CustomerTypeDto ctd) -> new CreateCustomer().apply(ctd, request))
                            // Uso el Mono Customer y lo guardo en el repositorio
                            // Devuelvo un Mono<Customer>
                            .flatMap(repository::insert)
                            // Uso el Mono<Customer> y lo transformo en un Mono<CustomerDtoResponse>
                            // y lo devuelve
                            .map(mapper::toDtoResponse);

                });
    }

    @Override
    public Mono<CustomerDtoResponse> update(Mono<CustomerDtoRequest> object, String id) {
        return repository.findById(id)
                .flatMap(
                        p -> object.map(mapper::toEntity)
                                .doOnNext(e -> e.setId(id))
                )
                .flatMap(repository::save)
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}