package com.eddo.springbootbackendapirest.model.service;

import com.eddo.springbootbackendapirest.model.entity.Cliente;
import com.eddo.springbootbackendapirest.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    void delete(Long id);

    public List<Region> findAllRegiones();

}
