/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.control.models.dao;

import com.control.control.models.entity.Personas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Beatriz
 */
public interface IPersonasDao extends JpaRepository<Personas,Long>{
    
}
