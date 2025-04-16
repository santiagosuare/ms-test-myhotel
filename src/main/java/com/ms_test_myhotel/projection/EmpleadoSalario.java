package com.ms_test_myhotel.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoSalario {
    private Long departamentoId;
    private String departamentoName;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long salary;
}

