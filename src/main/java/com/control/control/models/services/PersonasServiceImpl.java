/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.services;

import com.control.control.models.dao.IControlesDao;
import com.control.control.models.dao.IPersonasDao;
import com.control.control.models.entity.Controles;
import com.control.control.models.entity.Personas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Beatriz
 */
@Service
public class PersonasServiceImpl implements IPersonasService{
    
    @Autowired
    private IPersonasDao personasDao;
    
    @Autowired
    private IControlesDao controlesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Personas> findAll() {
       return (List<Personas>)personasDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Personas> findAll(Pageable pageable) {
        return personasDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Personas findById(Long id) {
        return personasDao.findById(id).orElse(null);
    }

    @Override
    public Personas save(Personas persona) {
        return personasDao.save(persona);
    }

    @Override
    public void delete(Long id) {
        personasDao.deleteById(id);
    }


    
}
