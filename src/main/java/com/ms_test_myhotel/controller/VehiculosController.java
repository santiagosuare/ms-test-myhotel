package com.ms_test_myhotel.controller;

import com.ms_test_myhotel.model.response.VehiculoResponse;
import com.ms_test_myhotel.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("vehiculosController")
@RequestMapping("vehiculos")
@RequiredArgsConstructor
public class VehiculosController {

    private final VehiculoService vehiculoService;

    @Operation(summary = "Obtener todos los vehículos")
    @GetMapping("/getVehiculos")
    public ResponseEntity<List<VehiculoResponse>> getVehiculos() {
        return ResponseEntity.ok(vehiculoService.getVehiculos());
    }

    @Operation(summary = "Obtener un vehículo por su patente")
    @GetMapping("/getVehiculo/{patente}")
    public ResponseEntity<VehiculoResponse> getVehiculo(@PathVariable String patente) {
        return ResponseEntity.ok(vehiculoService.getVehiculoByPatente(patente));
    }

    @Operation(summary = "Crear un vehículo de tipo automóvil o camión")
    @PostMapping("/createVehiculo")
    public ResponseEntity<VehiculoResponse> createVehiculo(@RequestBody VehiculoResponse vehiculoResponse) {
        return ResponseEntity.ok(vehiculoService.createVehiculo(vehiculoResponse));
    }

    @Operation(summary = "Borrar un vehículo por su patente")
    @DeleteMapping("/deleteVehiculo/{patente}")
    public ResponseEntity<String> deleteVehiculo(@PathVariable String patente) {
        vehiculoService.deleteVehiculo(patente);
        return ResponseEntity.ok("Vehículo eliminado con éxito");
    }

    @Operation(summary = "Actualizar un vehículo")
    @PutMapping("/updateVehiculo/{patente}")
    public ResponseEntity<VehiculoResponse> updateVehiculo(@PathVariable String patente, @RequestBody VehiculoResponse vehiculoResponse) {
        return ResponseEntity.ok(vehiculoService.updateVehiculo(patente, vehiculoResponse));
    }
}
