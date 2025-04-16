package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioSegmentoResponse {
    private String segmento;
    private int cantidadEmpleados;
}
