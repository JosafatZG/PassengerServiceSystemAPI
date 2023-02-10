package com.passengerservicesystemapi.controllers;

import com.passengerservicesystemapi.service.dao.GenericDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericController<E, S extends GenericDAO<E>> {
    private final S service;
    protected String nombreEntidad;


    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public List<E> getAll(){
        return (List<E>) service.findAll();
    }

    @GetMapping("/{id}")
    public E findById(@PathVariable(value = "id", required = false) Integer id){
        Optional<E> e = service.findById(id);
        if (e.isEmpty()){
            throw new RuntimeException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }

        return e.get();
    }

    @PostMapping
    public E save(@RequestBody E e){
        return service.save(e);
    }

    @PutMapping("/{id}")
    public E update(@PathVariable Integer id, @RequestBody E e){
        E eUpdate;
        Optional<E> oe = service.findById(id);
        if (oe.isEmpty()){
            throw new RuntimeException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        //eUpdate = oe.get();
        eUpdate = e;
        return service.save(eUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        Optional<E> e = service.findById(id);
        if (e.isEmpty()){
            throw new RuntimeException(
                    String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        service.deleteById(id);
    }
}
