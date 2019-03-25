package com.eddo.springbootbackendapirest.model.dao;

import com.eddo.springbootbackendapirest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IclienteDao extends CrudRepository<Cliente, Long> {


}
