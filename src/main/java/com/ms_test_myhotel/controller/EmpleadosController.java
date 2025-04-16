package com.ms_test_myhotel.controller;

import com.ms_test_myhotel.model.response.*;
import com.ms_test_myhotel.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("empleadosController")
@RequestMapping("empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    private final EmpleadoService empleadoService;

    @Operation(summary = "Obtener cantidad de empleados dentro de los siguientes segmentos de sueldos")
    @GetMapping("/getEmpleadosPorSegmentoSueldos")
    public ResponseEntity<List<SalarioSegmentoResponse>> getEmpleadosPorSegmentoSueldos() {
        return ResponseEntity.ok(empleadoService.getEmpleadosPorSegmentoSueldos());
    }

    @Operation(summary = "Obtener segmentos de sueldo, pero por departamento")
    @GetMapping("/getSegmentoSueldoDepartamento")
    public ResponseEntity<List<SalarioSegmentoDepartamentoResponse>> getSegmentoSueldoDepartamento() {
        return ResponseEntity.ok(empleadoService.getSegmentoSueldoDepartamento());
    }

    @Operation(summary = "Obtener el empleado con el mayor sueldo por departamento")
    @GetMapping("/getMayorSueldoDepartamento")
    public ResponseEntity<List<EmpleadoSalarioResponse>> getMayorSueldoEmpleado() {
        return ResponseEntity.ok(empleadoService.getMayorSueldoEmpleado());
    }

    @Operation(summary = "Obtener empleados que son gerentes y hayan sido contratados hace mas de 15 años")
    @GetMapping("/getEmpleadosGerenteTiempo")
    public ResponseEntity<List<EmpleadoGerenteTiempoResponse>> getEmpleadosGerenteTiempo() {
        return ResponseEntity.ok(empleadoService.getGerentesTiempo());
    }

    @Operation(summary = "Salario promedio de todos los departamentos que tengan más de 10 empleados.")
    @GetMapping("/getSalarioPromedioPorDepartamento")
    public ResponseEntity<List<SalarioPromedioResponse>> getSalarioPromedioPorDepartamento() {
        return ResponseEntity.ok(empleadoService.getSalarioPromedioPorDepartamento());
    }

    @Operation(summary = "Obtener la siguiente información agrupada por país: cantidad empleados, salario promedio, salario más alto, salario más bajo, promedio años antigüedad")
    @GetMapping("/getEmpleadoPorPais")
    public ResponseEntity<List<EmpleadoPaisResponse>> getEmpleadosPorPais() {
        return ResponseEntity.ok(empleadoService.getEmpleadosPorPais());
    }
}
