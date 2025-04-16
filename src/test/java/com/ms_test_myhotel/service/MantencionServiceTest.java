package com.ms_test_myhotel.service;

import com.ms_test_myhotel.model.dto.Mantencion;
import com.ms_test_myhotel.model.dto.Vehiculo;
import com.ms_test_myhotel.repository.MantencionRepository;
import com.ms_test_myhotel.repository.VehiculoRepository;
import com.ms_test_myhotel.model.response.MantencionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MantencionServiceTest {
    @Mock
    private MantencionRepository mantencionRepository;
    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private MantencionService mantencionService;

    @Test
    void createMantencion_deberiaCrearYRetornarMantencionResponse() {
        String patente = "ABC123";
        Vehiculo vehiculo = new Vehiculo(1L, "Toyota", "Corolla", patente, LocalDate.of(2020, 1, 1), 50000, "1.8");

        MantencionResponse request = new MantencionResponse();
        request.setDescripcion("Cambio de aceite");
        request.setCosto(25000L);

        Mantencion mantencionGuardada = new Mantencion(1L, LocalDate.now(), "Cambio de aceite", 25000L, vehiculo);

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.of(vehiculo));
        when(mantencionRepository.save(any())).thenReturn(mantencionGuardada);

        MantencionResponse response = mantencionService.createMantencion(patente, request);

        assertNotNull(response);
        assertEquals("Cambio de aceite", response.getDescripcion());
        assertEquals(25000.0, response.getCosto());
        assertEquals(patente, response.getPatente());
    }

    @Test
    void createMantencion_deberiaRetornarNullSiNoExisteVehiculo() {
        String patente = "XYZ789";
        MantencionResponse request = new MantencionResponse();
        request.setDescripcion("Revisión de frenos");
        request.setCosto(18000L);

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.empty());

        MantencionResponse response = mantencionService.createMantencion(patente, request);

        assertNull(response);
    }

    @Test
    void getMantenciones_deberiaRetornarListaMantencionResponse() {
        String patente = "DEF456";
        Vehiculo vehiculo = new Vehiculo(1L, "Ford", "Fiesta", patente, LocalDate.of(2018, 6, 1), 80000, "1.6");

        Mantencion m1 = new Mantencion(1L, LocalDate.now(), "Cambio de batería", 30000L, vehiculo);
        Mantencion m2 = new Mantencion(2L, LocalDate.now(), "Revisión general", 40000L, vehiculo);

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.of(vehiculo));
        when(mantencionRepository.findByPatente(patente)).thenReturn(List.of(m1, m2));

        List<MantencionResponse> response = mantencionService.getMantenciones(patente);

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Cambio de batería", response.get(0).getDescripcion());
    }

    @Test
    void getMantenciones_deberiaRetornarNullSiNoHayVehiculo() {
        String patente = "NOEXIST";

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.empty());

        List<MantencionResponse> response = mantencionService.getMantenciones(patente);

        assertNull(response);
    }

    @Test
    void getMantenciones_deberiaRetornarNullSiNoHayMantenciones() {
        String patente = "VACIO";
        Vehiculo vehiculo = new Vehiculo(1L, "Peugeot", "308", patente, LocalDate.of(2019, 3, 1), 60000, "1.6");

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.of(vehiculo));
        when(mantencionRepository.findByPatente(patente)).thenReturn(List.of());

        List<MantencionResponse> response = mantencionService.getMantenciones(patente);

        assertNull(response);
    }

    @Test
    void deleteMantencion_deberiaEliminarCorrectamente() {
        String patente = "DEL123";
        Long mantencionId = 99L;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(patente);
        Mantencion mantencion = new Mantencion(mantencionId, LocalDate.now(), "Control motor", 20000L, vehiculo);

        when(vehiculoRepository.findByPatente(patente)).thenReturn(Optional.of(vehiculo));
        when(mantencionRepository.findById(mantencionId)).thenReturn(Optional.of(mantencion));

        assertDoesNotThrow(() -> mantencionService.deleteMantencion(patente, mantencionId));
        verify(mantencionRepository).deleteById(mantencionId);
    }
}
