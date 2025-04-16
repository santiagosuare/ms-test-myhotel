package com.ms_test_myhotel.model.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoVehiculo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AutomovilResponse.class, name = "AUTOMOVIL"),
        @JsonSubTypes.Type(value = CamionResponse.class, name = "CAMION")
})
@Schema(discriminatorProperty = "tipoVehiculo",
        oneOf = {AutomovilResponse.class, CamionResponse.class})
public abstract class VehiculoResponse {
    private String marca;
    private String modelo;
    private String patente;
    private LocalDate anioFabricacion;
    private long kilometraje;
    private String cilindrada;
}
