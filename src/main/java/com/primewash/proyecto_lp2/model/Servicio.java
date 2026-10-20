package com.primewash.proyecto_lp2.model;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Servicio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codser")
    private Integer codser;

    @Column(name = "nomser")
    private String nomser;

    @Column(name = "desser")
    private String desser;

    @Column(name = "preser")
    private BigDecimal preser;

    @Column(name = "duracion_min")
    private Integer duracionMin;

    @Column(name = "estado")
    private Boolean estado;
}