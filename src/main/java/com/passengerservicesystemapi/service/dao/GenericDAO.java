package com.passengerservicesystemapi.service.dao;

import java.util.Optional;

public interface GenericDAO<E>{
    Optional<E> findById(Integer id);
    E save(E e);
    Iterable<E> findAll();
    void deleteById(Integer id);
}
