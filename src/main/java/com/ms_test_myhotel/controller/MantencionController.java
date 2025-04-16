package com.ms_test_myhotel.controller;

import com.ms_test_myhotel.model.response.MantencionResponse;
import com.ms_test_myhotel.service.MantencionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("mantencionController")
@RequestMapping("mantencion")
@RequiredArgsConstructor
public class MantencionController {

    private final MantencionService mantencionService;

    @Operation(summary = "Crear una mantención para un vehículo")
    @PostMapping("/createMantencion/{patente}")
    public ResponseEntity<MantencionResponse> createMantencion(@PathVariable String patente, @RequestBody MantencionResponse mantencionResponse) {
        return ResponseEntity.ok(mantencionService.createMantencion(patente, mantencionResponse));
    }

    @Operation(summary = "Obtener todas las mantenciones de un vehículo")
    @GetMapping("/getMantenciones/{patente}")
    public ResponseEntity<List<MantencionResponse>> getMantenciones(@PathVariable String patente) {
        return ResponseEntity.ok(mantencionService.getMantenciones(patente));
    }

    @Operation(summary = "Borrar una mantención por su patente y id")
    @DeleteMapping("/deleteMantencion/{patente}/{id}")
    public ResponseEntity<String> deleteMantencion(@PathVariable String patente, @PathVariable Long id) {
        mantencionService.deleteMantencion(patente, id);
        return ResponseEntity.ok("Mantención eliminada con éxito");
    }

}
