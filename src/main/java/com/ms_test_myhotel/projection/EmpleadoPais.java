package com.ms_test_myhotel.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoPais {
    private String pais;
    private int cantidadEmpleados;
    private Double salarioPromedio;
    private Double salarioMaximo;
    private Double salarioMinimo;
    private Double promedioAntiguedadAnios;
}
