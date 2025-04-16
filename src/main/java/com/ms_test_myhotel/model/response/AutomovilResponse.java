package com.ms_test_myhotel.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomovilResponse extends VehiculoResponse {
    private String tipo;
    private long cantPuertas;
    private long cantPasajeros;
    private long cantMaletero;
}
