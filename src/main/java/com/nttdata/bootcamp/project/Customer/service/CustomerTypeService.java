package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoResponse;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>Customer Type Service</h1>
 * Esta clase tiene la finalidad de concentrar la logica necesaria para el CRUD de los objetos CustomerType
 *
 * @author Grupo06
 * @version 1.0
 * @since 2022-10-14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerTypeService implements ICustomerTypeService<CustomerTypeDtoRequest, CustomerTypeDtoResponse> {
    @Autowired
    private final ICustomerTypeRepository repository;
    @Autowired
    private final ICustomerTypeMapper mapper;

    public ResponseEntity<CustomerTypeDtoResponse> buildOkResponse(CustomerTypeDtoResponse customerTypeDtoResponse) {
        return new ResponseEntity<>(customerTypeDtoResponse, HttpStatus.OK);
    }

    public ResponseEntity<CustomerTypeDtoResponse> buildBadRequestResponse() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<CustomerTypeDtoResponse> buildNotFoundResponse() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean validate(CustomerTypeDtoRequest customerTypeDtoRequest) {
        return !customerTypeDtoRequest.getDescription().isBlank();
    }

    @Override
    public Flux<CustomerTypeDtoResponse> getAll() {
        return repository.findAll()
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> getById(String id) {
        return repository.findById(id)
                .map(mapper::toDtoResponse)
                .map(this::buildOkResponse)
                .defaultIfEmpty(this.buildNotFoundResponse());
    }

    @Override
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> save(Mono<CustomerTypeDtoRequest> object) {
        return object.filter(this::validate)
                // convierte el DtoRequest en Entity
                .map(mapper::toEntity)
                // Guarda la entidad en el repositorio
                .flatMap(repository::insert)
                // Convierte el Entity en DtoResponse
                .map(mapper::toDtoResponse)
                // Devuelve ResponseEntity con la cabecera 200
                .map(this::buildOkResponse)
                // Si no tiene datos en el description devuelve 400
                .defaultIfEmpty(this.buildBadRequestResponse());
    }

    @Override
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> update(Mono<CustomerTypeDtoRequest> object, String id) {
        // Devuelve el elemento segun el ID
        return repository.findById(id)
                .flatMap(element -> object.filter(this::validate)
                        // convierte el DtoRequest en Entity
                        .map(mapper::toEntity)
                        // setea el id en la nueva entidad
                        .doOnNext(e -> e.setId(id))
                        // Guarda la entidad en el repositorio
                        .flatMap(repository::save)
                        // Convierte el Entity en DtoResponse
                        .map(mapper::toDtoResponse)
                        // Devuelve ResponseEntity con la cabecera 200
                        .map(this::buildOkResponse)
                        // Si no tiene datos en el description devuelve 400
                        .defaultIfEmpty(this.buildBadRequestResponse())
                )
                // Si no hay elemento devuelve 404
                .defaultIfEmpty(this.buildNotFoundResponse());
    }

    @Override
    public Mono<ResponseEntity<CustomerTypeDtoResponse>> delete(String id) {
        return repository.findById(id)
                .flatMap(deleteCustomerType -> repository.delete(deleteCustomerType)
                        .then(Mono.just(this.buildOkResponse(mapper.toDtoResponse(deleteCustomerType)))))
                .defaultIfEmpty(this.buildNotFoundResponse());
    }
}