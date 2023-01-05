/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.controllers;

import com.control.control.models.entity.Controles;
import com.control.control.models.services.IControlesService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Beatriz
 */
@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api")
public class ControlesRestController {
    
    @Autowired
    private IControlesService controlService;
    
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/controles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Controles mostrarControl(@PathVariable Long id){
        return controlService.findControlById(id);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/controles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity <?> delete (@PathVariable Long id){
        
        Map<String, Object> response =new HashMap<>();
        try{
        controlService.deleteControlById(id);
        }
        catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar el control en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }   
        response.put("Mensaje", "Control eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/controles")
    @ResponseStatus(HttpStatus.CREATED)
    public Controles nuevoControl(@RequestBody Controles control){
        return controlService.saveControl(control);
    }
    
    
    @Secured("ROLE_ADMIN")
    @PutMapping("/controles/{id}")
    public ResponseEntity <?> modificarPersona(@Valid @RequestBody Controles control,BindingResult result,@PathVariable Long id){
        
        Controles controlActual = controlService.findControlById(id);
        Controles controlActualizado = null;
        Map<String, Object> response =new HashMap<>();
        
        if(result.hasErrors()){
              
            List<String> errors = result.getFieldErrors()
                                .stream()
                                .map(err -> "El campo " + err.getField() + " " +err.getDefaultMessage())
                                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        
        if(controlActual == null){
            response.put("mensaje", "El control ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        
        try{
            controlActual.setCalorias(control.getCalorias());
            controlActual.setEdad_metabolica(control.getEdad_metabolica());
            controlActual.setFecha(control.getFecha());
            controlActual.setGrasa(control.getGrasa());
            controlActual.setImc(control.getImc());
            controlActual.setMusculo(control.getMusculo());
            controlActual.setPeso(control.getPeso());
            controlActual.setVisceral(control.getVisceral());
            
            
            controlActualizado = controlService.saveControl(controlActual);
            
        }catch(DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El control ha sido actualizado con exito");
        response.put("control", controlActualizado);
        return new ResponseEntity <Map<String,Object>>(response,HttpStatus.CREATED);
    }
    
}
