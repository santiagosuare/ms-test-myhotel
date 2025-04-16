package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantencionResponse {
    private Long id;
    private LocalDate fechaMantencion;
    private String descripcion;
    private long costo;
    private String patente;
}
