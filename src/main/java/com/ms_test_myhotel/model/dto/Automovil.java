package com.ms_test_myhotel.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "automovil")
@NoArgsConstructor
@AllArgsConstructor
public class Automovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "cant_puertas")
    private long cantPuertas;
    @Column(name = "cant_pasajeros")
    private long cantPasajeros;
    @Column(name = "cant_maletero")
    private long cantMaletero;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private Vehiculo vehiculo;

}
