package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerDtoResponse;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoRequest;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDtoResponse;
import com.nttdata.bootcamp.project.Customer.entity.Customer;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerRepository;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerMapper;
import com.nttdata.bootcamp.project.Customer.utils.ICustomerTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private final ICustomerTypeMapper mapperType;
    @Value("${message.uri}")
    String uri;

    public ResponseEntity<CustomerDtoResponse> buildOkResponse(CustomerDtoResponse customerDtoResponse) {
        return new ResponseEntity<>(customerDtoResponse, HttpStatus.OK);
    }

    public ResponseEntity<CustomerDtoResponse> buildBadRequestResponse() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<CustomerDtoResponse> buildNotFoundResponse() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean validate(CustomerDtoRequest customerDtoRequest) {
        return !(customerDtoRequest.getFirstname().isBlank() ||
                customerDtoRequest.getLastname().isBlank() ||
                customerDtoRequest.getCustomerTypeId().isBlank());
    }

    public Customer buildCustomer(CustomerType customerType, CustomerDtoRequest customerDtoRequest) {
        Customer customer = new Customer();
        customer.setFirstname(customerDtoRequest.getFirstname());
        customer.setLastname(customerDtoRequest.getLastname());
        customer.setCustomerTypeId(customerDtoRequest.getCustomerTypeId());
        customer.setCustomerType(customerType);
        return customer;
    }

    @Override
    public Flux<CustomerDtoResponse> getAll() {
        return repository.findAll()
                .map(mapper::toDtoResponse);
    }

    @Override
    public Mono<ResponseEntity<CustomerDtoResponse>> getById(String id) {
        return repository.findById(id)
                .map(mapper::toDtoResponse)
                .map(this::buildOkResponse)
                .defaultIfEmpty(this.buildNotFoundResponse());
    }
    public boolean validate2(ResponseEntity<CustomerTypeDtoResponse> item)
    {
        return item.getStatusCode().is4xxClientError() || item.getStatusCode().is5xxServerError();
    }
    @Override
    public Mono<ResponseEntity<CustomerDtoResponse>> save(Mono<CustomerDtoRequest> object) {
        return object.filter(this::validate)
                .flatMap(
                    (request) ->
                    {
                        // Llamo a un consumer y devuelvo el Mono de Tipo a usar
                        Mono<ResponseEntity<Object>> customerTypeDtoMono = service.getById(request.getCustomerTypeId());
                        // Uso el Mono de tipo y lo convierto en Mono Customer
                        return customerTypeDtoMono
                                .flatMap(
                                p -> {
                                    if(p.getStatusCode().is4xxClientError() || p.getStatusCode().is5xxServerError())
                                    {
                                        return Mono.just(this.buildBadRequestResponse());
                                    }
                                    CustomerTypeDtoResponse customerTypeDtoResponse = (CustomerTypeDtoResponse) p.getBody();
                                    return Mono.just(this.buildCustomer(mapperType.toEntity(customerTypeDtoResponse), request))
                                        // Guarda la entidad en el repositorio
                                        .flatMap(repository::insert)
                                        // Convierte el Entity en DtoResponse
                                        .map(mapper::toDtoResponse)
                                        // Devuelve ResponseEntity con la cabecera 200
                                        .map(this::buildOkResponse);
                                })
                                // Si no tiene datos en el description devuelve 400
                                .defaultIfEmpty(this.buildBadRequestResponse());
                })
                // Si no tiene datos correctos devuelve 400
                .defaultIfEmpty(this.buildBadRequestResponse());
    }


    @Override
    public Mono<ResponseEntity<CustomerDtoResponse>> update(Mono<CustomerDtoRequest> object, String id) {
        // Busca el Customer por ID
        return repository.findById(id)
                // Si existe el customer
                .flatMap(element ->
                        object.filter(this::validate)
                                .flatMap(request -> {
                                    Mono<ResponseEntity<CustomerTypeDtoResponse>> a = service.getById(request.getCustomerTypeId());
                                    return a.flatMap(p -> Mono.just(this.buildCustomer(mapperType.toEntity(p.getBody()), request))
                                                    .doOnNext(e -> e.setId(id))
                                                    .flatMap(repository::save)
                                                    .map(mapper::toDtoResponse)
                                                    .map(this::buildOkResponse))
                                            .defaultIfEmpty(this.buildBadRequestResponse());
                                })
                )
                // Si no existe el customer
                .defaultIfEmpty(this.buildNotFoundResponse());
    }

    @Override
    public Mono<ResponseEntity<CustomerDtoResponse>> delete(String id) {
        return repository.findById(id)
                .flatMap(deleteCustomerType -> repository.delete(deleteCustomerType)
                        .then(Mono.just(this.buildOkResponse(mapper.toDtoResponse(deleteCustomerType)))))
                .defaultIfEmpty(this.buildNotFoundResponse());
    }
}