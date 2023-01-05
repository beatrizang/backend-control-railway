/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.control.models.services;

import com.control.control.models.entity.Controles;
import com.control.control.models.entity.Personas;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Beatriz
 */
public interface IPersonasService {
    public List<Personas> findAll();
    
    public Page<Personas> findAll(Pageable pageable);
    
    public Personas findById(Long id);
    
    public Personas save(Personas persona);
    
    public void delete(Long id);
    
}
