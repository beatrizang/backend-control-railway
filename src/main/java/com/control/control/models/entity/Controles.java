/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name="Controles")
public class Controles implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_control;
    
    private Date fecha;
    
    private Double peso;
    
    private Double grasa;
    
    private Double imc;
    
    private Double musculo;
    
    private Long calorias;
    
    private Long edad_metabolica;
    
    private Long visceral;
    
    private String descripcion;
    
    //Many corresponde a factura y one a la clase persona
    @JsonIgnoreProperties(value={"controles","hibernateLazyInitializer","handler"},allowSetters=true)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_persona")
    private Personas persona;

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    public Long getId_control() {
        return id_control;
    }

    public void setId_control(Long id_control) {
        this.id_control = id_control;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getGrasa() {
        return grasa;
    }

    public void setGrasa(Double grasa) {
        this.grasa = grasa;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public Double getMusculo() {
        return musculo;
    }

    public void setMusculo(Double musculo) {
        this.musculo = musculo;
    }

    public Long getCalorias() {
        return calorias;
    }

    public void setCalorias(Long calorias) {
        this.calorias = calorias;
    }

    public Long getEdad_metabolica() {
        return edad_metabolica;
    }

    public void setEdad_metabolica(Long edad_metabolica) {
        this.edad_metabolica = edad_metabolica;
    }

    public Long getVisceral() {
        return visceral;
    }

    public void setVisceral(Long visceral) {
        this.visceral = visceral;
    }
    
    private static final long serialVersionUID = 1L;
}
