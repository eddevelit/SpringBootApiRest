package com.eddo.springbootbackendapirest.model.dao;

import com.eddo.springbootbackendapirest.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    /*
    @Query("select  u from Usuario where u.username=?1")
    public Usuario findByUsername2(String username);
    */
}
