package com.passengerservicesystemapi.controllers;

import com.passengerservicesystemapi.entity.Persona;
import com.passengerservicesystemapi.service.dao.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.micro.pss.persona}")
public class PersonaController extends GenericController<Persona, PersonaDAO>{

    @Autowired
    public PersonaController(PersonaDAO service) {
        super(service);
        this.nombreEntidad = "personas";
    }
}
