package com.ms_test_myhotel.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "mantencion")
@NoArgsConstructor
@AllArgsConstructor
public class Mantencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_mantencion")
    private LocalDate fechaMantencion;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "costo")
    private long costo;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

}
