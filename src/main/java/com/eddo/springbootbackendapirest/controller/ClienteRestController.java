package com.eddo.springbootbackendapirest.controller;

import com.eddo.springbootbackendapirest.model.entity.Cliente;
import com.eddo.springbootbackendapirest.model.entity.Region;
import com.eddo.springbootbackendapirest.model.service.IClienteService;
import com.eddo.springbootbackendapirest.model.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        return clienteService.findAll(pageable);
    }

    @GetMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable Long id) {

        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteService.findById(id);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (cliente == null ){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

        Cliente cliente1 = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cliente1 = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido creado con éxito");
        response.put("cliente", cliente1);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result ,@PathVariable Long id){

        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo `" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (clienteActual == null){
            response.put("mensaje", "Error: no se pudo editar, El cliente con Id: ".concat(id.toString().concat(" no existe ne la Base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());
            clienteActual.setRegion(cliente.getRegion());

            clienteUpdated= clienteService.save(clienteActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", clienteUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/clientes/{id}")
     public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        try{
            Cliente cliente = clienteService.findById(id);
            String nombreFotoAnterior = cliente.getFoto();

            uploadFileService.eliminar(nombreFotoAnterior);

            clienteService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido elimiando con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
     @PostMapping("/clientes/upload")
     public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteService.findById(id);

        if (!archivo.isEmpty()){
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = cliente.getFoto();

            uploadFileService.eliminar(nombreFotoAnterior);

            cliente.setFoto(nombreArchivo);
            clienteService.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

        }



        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
     }

     @GetMapping("/uploads/img/{nombreFoto:.+}")
     public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){


         Resource recurso = null;

         try {
             recurso = uploadFileService.cargar(nombreFoto);
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         HttpHeaders cabecera = new HttpHeaders();
         cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()+ "\"");
         return new ResponseEntity<Resource>(recurso, cabecera ,HttpStatus.OK);

     }

    @Secured({"ROLE_ADMIN"})
     @GetMapping("/clientes/regiones")
     public List<Region> listarRegiones(){
        return clienteService.findAllRegiones();
     }
}