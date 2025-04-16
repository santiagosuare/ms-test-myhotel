package com.ms_test_myhotel.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "vehiculo")
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "patente")
    private String patente;
    @Column(name = "anio_fabricacion")
    private LocalDate anioFabricacion;
    @Column(name = "kilometraje")
    private long kilometraje;
    @Column(name = "cilindrada")
    private String cilindrada;
}
