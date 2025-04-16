package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioPromedioResponse {

    private Long departamentoId;
    private String departamentoName;
    private Double salarioPromedio;
    private int cantidadEmpleados;
}
