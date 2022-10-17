package com.nttdata.bootcamp.project.Customer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>IGeneralService</h1>
 * Interface general con las principales funciones crud
 * @param <T>
 * @author Grupo05
 * @version 1.0
 * @since 2022-10-14
 */
public interface IGeneralService <T,R>{
    /**
     * Función generica que nos devuelve todos los objetos guardados en la coleccion segun el tipo T definido
     * @return Flux<T>
     */
    Flux<R> getAll();

    /**
     * Función generica que solicita una cadena para usarla como id en la coleccion segun el tipo T definido
     * @param id representa el campo id de la coleccion
     * @return   retorna una variable Mono<T>
     */
    Mono<R> getById(String id);

    /**
     * Función generica que recibe un objeto del tipo Mono<T> para guardarlo en la colección segun el tipo T definido
     * @param object  Es variable del tipo Mono<T> para ser guardado en la collección
     * @return        Retorna la misma variable Mono<T>
     */
    Mono<R> save(Mono<T> object);

    /**
     * Función generica que recibe un objeto del tipo Mono<T> para actualizarlo en la colección segun el tipo T definido
     * @param object  Es variable del tipo Mono<T> para ser actualizada en la collección
     * @param id      Representa el campo id de la coleccion que se va a editar
     *@return         Retorna la misma variable Mono<T>
     */
    Mono<R> update(Mono<T> object, String id);

    /**
     * Función generica que se encarga de eliminar un documento en la colección según el tipo T definido
     * @param id  Representa el campo id de la coleccion que se va a editar
     * @return    Retorna un Mono<Void>
     */
    Mono<Void> delete(String id);
}
