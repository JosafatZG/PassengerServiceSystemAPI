package com.passengerservicesystemapi.service.impl;

import com.passengerservicesystemapi.entity.Persona;
import com.passengerservicesystemapi.repository.PersonaRepository;
import com.passengerservicesystemapi.service.dao.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaDAOImpl extends GenericDAOImpl<Persona, PersonaRepository> implements PersonaDAO {

    @Autowired
    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }
}
