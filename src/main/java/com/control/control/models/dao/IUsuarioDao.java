/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.control.models.dao;

import com.control.control.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Beatriz
 */
public interface IUsuarioDao extends CrudRepository<Usuario,Long>{
    public Usuario findByUsername(String username);
   
}
