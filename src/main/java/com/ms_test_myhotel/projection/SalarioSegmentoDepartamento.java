package com.ms_test_myhotel.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioSegmentoDepartamento {
    private Long departamentoId;
    private String departamentoNombre;
    private String segmento;
    private int cantidadEmpleados;
}
