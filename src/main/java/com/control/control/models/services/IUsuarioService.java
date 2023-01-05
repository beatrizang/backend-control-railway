/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.control.models.services;

import com.control.control.models.entity.Usuario;

/**
 *
 * @author Beatriz
 */
public interface IUsuarioService {
    
    public Usuario findByUsername(String username);
    
}
