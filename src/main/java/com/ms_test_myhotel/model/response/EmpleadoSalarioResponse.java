package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoSalarioResponse {
    private Long departamentoId;
    private String departamentoName;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long salary;
}
