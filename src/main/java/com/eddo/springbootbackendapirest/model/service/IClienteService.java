package com.eddo.springbootbackendapirest.model.service;

import com.eddo.springbootbackendapirest.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
}
