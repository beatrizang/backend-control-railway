/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.control.models.services;

import com.control.control.models.entity.Controles;

/**
 *
 * @author Beatriz
 */
public interface IControlesService {
    
    public Controles findControlById(Long id);
    
    public Controles saveControl(Controles control);
    
    public void deleteControlById(Long id);
}
