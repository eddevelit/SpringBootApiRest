package com.eddo.springbootbackendapirest.model.service;

import com.eddo.springbootbackendapirest.model.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
