/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.controllers;

import com.control.control.models.entity.Personas;
import com.control.control.models.services.IPersonasService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Beatriz
 */
@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api")
public class PersonasRestController {
    
    @Autowired
    private IPersonasService personaService;
    
    @GetMapping("/personas")
    public List<Personas> listarPersonas(){
        return personaService.findAll();
    }
    
    @GetMapping("/personas/page/{page}")
    public Page<Personas> listarPersonas(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        return personaService.findAll(pageable);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/personas/{id}")
    public ResponseEntity <?> mostrarPersona(@PathVariable Long id){
        Personas persona = null;
        Map<String, Object> response =new HashMap<>();
        
        try{
            persona = personaService.findById(id);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        
        if(persona == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Personas>(persona,HttpStatus.OK);
    }
    
    @Secured("ROLE_ADMIN")
    @PostMapping("/personas")
    public ResponseEntity <?> nuevaPersona(@Valid @RequestBody Personas persona, BindingResult result){
        
        Personas nuevaPersona = null;
        Map<String, Object> response =new HashMap<>();
        
        if(result.hasErrors()){
              
            List<String> errors = result.getFieldErrors()
                                .stream()
                                .map(err -> "El campo " + err.getField() + " " +err.getDefaultMessage())
                                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        
        try{
            nuevaPersona = personaService.save(persona);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }   
        response.put("mensaje","El cliente ha sido creado con exito");
        response.put("persona", nuevaPersona);
        return new ResponseEntity <Map<String,Object>>(response,HttpStatus.CREATED);
    }
    
    @Secured("ROLE_ADMIN")
    @PutMapping("/personas/{id}")
    public ResponseEntity <?> modificarPersona(@Valid @RequestBody Personas persona,BindingResult result,@PathVariable Long id){
        
        Personas personaActual = personaService.findById(id);
        Personas personaActualizada = null;
        Map<String, Object> response =new HashMap<>();
        
        if(result.hasErrors()){
              
            List<String> errors = result.getFieldErrors()
                                .stream()
                                .map(err -> "El campo " + err.getField() + " " +err.getDefaultMessage())
                                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        
        if(personaActual == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        
        try{
            personaActual.setApellido(persona.getApellido());
            personaActual.setNombre(persona.getNombre());
            personaActual.setFecha_nac(persona.getFecha_nac());
            personaActual.setAltura(persona.getAltura());
            
            personaActualizada = personaService.save(personaActual);
            
        }catch(DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido actualizado con exito");
        response.put("persona", personaActualizada);
        return new ResponseEntity <Map<String,Object>>(response,HttpStatus.CREATED);
    }
    
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/personas/{id}")
    public ResponseEntity <?> eliminarPersona(@PathVariable Long id){
        
        Map<String, Object> response =new HashMap<>();
        
        try{
           personaService.delete(id); 
        }catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }   
        response.put("Mensaje", "Cliente eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}

