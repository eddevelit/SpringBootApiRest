package com.eddo.springbootbackendapirest.model.dao;

import com.eddo.springbootbackendapirest.model.entity.Cliente;
import com.eddo.springbootbackendapirest.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IclienteDao extends JpaRepository<Cliente, Long> {
    @Query("from Region ")
    public List<Region> findAllRegiones();


}
