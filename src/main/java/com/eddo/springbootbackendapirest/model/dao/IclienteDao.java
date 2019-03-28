package com.eddo.springbootbackendapirest.model.dao;

import com.eddo.springbootbackendapirest.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IclienteDao extends JpaRepository<Cliente, Long> {




}
