package com.primewash.proyecto_lp2.model;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

public class Empleado {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codemp")
    private Integer codEmpleado;

    @Column(name = "tipodoc")
    private String tipoDocumento;

    @Column(name = "docid")
    private String docId;

    @Column(name = "nomemp")
    private String nomEmpleado;

    @Column(name = "apepemp")
    private String apePaternoEmpleado;

    @Column(name = "apememp")
    private String apeMaternoEmpleado;

    @Column(name = "fecemp")
    private LocalDate fecEmpleado;

    @Column(name = "diremp")
    private String dirEmpleado;

    @Column(name = "celemp")
    private String celEmpleado;

    @Column(name = "coremp")
    private String corEmpleado;

    @Column(name = "usuemp")
    private String usuEmpleado;

    @Column(name = "claemp")
    private String claEmpleado;

    @ManyToOne
    @JoinColumn(name = "coddis")
    private Distrito distrito;

    @ManyToOne
    @JoinColumn(name = "codrol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "codsex")
    private Sexo sexo;
}