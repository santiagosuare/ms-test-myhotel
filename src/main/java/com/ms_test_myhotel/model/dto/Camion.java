package com.ms_test_myhotel.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "camion")
@NoArgsConstructor
@AllArgsConstructor
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "capacidad_toneladas")
    private long capacidadToneladas;
    @Column(name = "cant_ejes")
    private long cantEjes;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private Vehiculo vehiculo;
}
