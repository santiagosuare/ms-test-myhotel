package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioSegmentoDepartamentoResponse {
    private Long departamentoId;
    private String departamentoNombre;
    private String segmento;
    private int cantidadEmpleados;
}
