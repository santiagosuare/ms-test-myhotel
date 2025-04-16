package com.ms_test_myhotel.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioPromedio {
    private Long departamentoId;
    private String departamentoName;
    private Double salarioPromedio;
    private int cantidadEmpleados;
}
