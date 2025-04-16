package com.ms_test_myhotel.service;

import com.ms_test_myhotel.model.response.*;
import com.ms_test_myhotel.projection.*;
import com.ms_test_myhotel.repository.SalarioSegmentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private SalarioSegmentoRepository salarioSegmentoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void getEmpleadosPorSegmentoSueldos_deberiaRetornarLista() {
        SalarioSegmento segmento = mock(SalarioSegmento.class);
        when(segmento.getSegmento()).thenReturn("0 - 1000");
        when(segmento.getCantidadEmpleados()).thenReturn(5);

        when(salarioSegmentoRepository.getSegments()).thenReturn(List.of(segmento));
        when(segmento.getCantidadEmpleados()).thenReturn(10);

        when(salarioSegmentoRepository.getSegments()).thenReturn(List.of(segmento));

        List<SalarioSegmentoResponse> result = empleadoService.getEmpleadosPorSegmentoSueldos();

        assertEquals(1, result.size());
        assertEquals("0 - 1000", result.get(0).getSegmento());
    }

    @Test
    void getSegmentoSueldoDepartamento_deberiaRetornarLista() {
        SalarioSegmentoDepartamento depto = mock(SalarioSegmentoDepartamento.class);
        when(depto.getDepartamentoId()).thenReturn(10L);
        when(depto.getDepartamentoNombre()).thenReturn("Marketing");
        when(depto.getSegmento()).thenReturn("2000 - 4000");
        when(depto.getCantidadEmpleados()).thenReturn(5);

        when(salarioSegmentoRepository.getSegmentsByDeparmanet()).thenReturn(List.of(depto));

        List<SalarioSegmentoDepartamentoResponse> result = empleadoService.getSegmentoSueldoDepartamento();

        assertEquals(1, result.size());
        assertEquals("Marketing", result.get(0).getDepartamentoNombre());
    }

    @Test
    void getMayorSueldoEmpleado_deberiaRetornarLista() {
        EmpleadoSalario empleado = mock(EmpleadoSalario.class);
        when(empleado.getDepartamentoId()).thenReturn(1L);
        when(empleado.getDepartamentoName()).thenReturn("IT");
        when(empleado.getEmployeeId()).thenReturn(100L);
        when(empleado.getFirstName()).thenReturn("Juan");
        when(empleado.getLastName()).thenReturn("Pérez");
        when(empleado.getSalary()).thenReturn(9999L);

        when(salarioSegmentoRepository.getMayorSueldoEmpleado()).thenReturn(List.of(empleado));

        List<EmpleadoSalarioResponse> result = empleadoService.getMayorSueldoEmpleado();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getFirstName());
    }

    @Test
    void getGerentesTiempo_deberiaRetornarLista() {
        EmpleadoGerenteTiempo gerente = mock(EmpleadoGerenteTiempo.class);
        when(gerente.getEmployeeId()).thenReturn(200L);
        when(gerente.getFirstName()).thenReturn("Lucía");
        when(gerente.getLastName()).thenReturn("Gómez");
        when(gerente.getHireDate()).thenReturn(Date.valueOf(LocalDate.of(2005, 1, 1)));
        when(gerente.getJobTitle()).thenReturn("Sales Manager");

        when(salarioSegmentoRepository.getGerentesTiempo()).thenReturn(List.of(gerente));

        List<EmpleadoGerenteTiempoResponse> result = empleadoService.getGerentesTiempo();

        assertEquals(1, result.size());
        assertEquals("Lucía", result.get(0).getFirstName());
    }

    @Test
    void getSalarioPromedioPorDepartamento_deberiaRetornarLista() {
        SalarioPromedio promedio = mock(SalarioPromedio.class);
        when(promedio.getDepartamentoId()).thenReturn(30L);
        when(promedio.getDepartamentoName()).thenReturn("RRHH");
        when(promedio.getSalarioPromedio()).thenReturn(4500.0);
        when(promedio.getCantidadEmpleados()).thenReturn(8);

        when(salarioSegmentoRepository.getSalarioPromedioPorDepartamento()).thenReturn(List.of(promedio));

        List<SalarioPromedioResponse> result = empleadoService.getSalarioPromedioPorDepartamento();

        assertEquals(1, result.size());
        assertEquals(4500.0, result.get(0).getSalarioPromedio());
    }

    @Test
    void getEmpleadosPorPais_deberiaRetornarLista() {
        EmpleadoPais empleado = mock(EmpleadoPais.class);
        when(empleado.getPais()).thenReturn("Argentina");
        when(empleado.getCantidadEmpleados()).thenReturn(100);
        when(empleado.getSalarioPromedio()).thenReturn(3000.0);
        when(empleado.getSalarioMaximo()).thenReturn(5000.0);
        when(empleado.getSalarioMinimo()).thenReturn(1000.0);
        when(empleado.getPromedioAntiguedadAnios()).thenReturn(5.5);

        when(salarioSegmentoRepository.getEmpleadosPorPais()).thenReturn(List.of(empleado));

        List<EmpleadoPaisResponse> result = empleadoService.getEmpleadosPorPais();

        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getPais());
        assertEquals(5.5, result.get(0).getPromedioAntiguedadAnios());
    }
}
