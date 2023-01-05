/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.services;

import com.control.control.models.dao.IControlesDao;
import com.control.control.models.entity.Controles;
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
public class ControlesServiceImpl implements IControlesService{
    
    @Autowired
    private IControlesDao controlesDao;

    @Override
    @Transactional(readOnly = true)
    public Controles findControlById(Long id) {
        return controlesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Controles saveControl(Controles control) {
        return controlesDao.save(control);
    }

    @Override
    @Transactional
    public void deleteControlById(Long id) {
        controlesDao.deleteById(id);
    }

    
}
