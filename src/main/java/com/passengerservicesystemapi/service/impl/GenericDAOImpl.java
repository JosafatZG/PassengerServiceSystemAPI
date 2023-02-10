package com.passengerservicesystemapi.service.impl;

import com.passengerservicesystemapi.service.dao.GenericDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericDAOImpl<E, R extends CrudRepository<E, Integer>> implements GenericDAO<E> {

    protected final R repository;

    public GenericDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
