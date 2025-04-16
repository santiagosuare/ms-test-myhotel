package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoGerenteTiempoResponse {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String jobTitle;
}
