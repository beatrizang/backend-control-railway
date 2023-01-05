/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name="Personas")
public class Personas implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_persona;
    
    @NotEmpty
    @Size(min=4,max=20)
    @Column(nullable=false)
    private String nombre;
    
    @NotEmpty
    @Size(min=4,max=20)
    @Column(nullable=false)
    private String apellido;
    
    
    private Date fecha_nac;
    
    private Long altura;
    
    @JsonIgnoreProperties(value={"persona","hibernateLazyInitializer","handler"},allowSetters=true)
    @OneToMany(fetch=FetchType.LAZY, mappedBy="persona",cascade=CascadeType.ALL)
    private List<Controles> controles;

    public Personas() {
        this.controles = new ArrayList<>();
    }
    
    public List<Controles> getControles() {
        return controles;
    }

    public void setControles(List<Controles> controles) {
        this.controles = controles;
    }

    
    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }
    
    private static final long serialVersionUID = 1L;
    
}
