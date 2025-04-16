package com.ms_test_myhotel.service;

import com.ms_test_myhotel.model.dto.Automovil;
import com.ms_test_myhotel.model.dto.Camion;
import com.ms_test_myhotel.model.dto.Vehiculo;
import com.ms_test_myhotel.repository.AutomovilRepository;
import com.ms_test_myhotel.repository.CamionRepository;
import com.ms_test_myhotel.repository.VehiculoRepository;
import com.ms_test_myhotel.model.response.AutomovilResponse;
import com.ms_test_myhotel.model.response.CamionResponse;
import com.ms_test_myhotel.model.response.VehiculoResponse;
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
class VehiculoServiceTest {
    @Mock
    private AutomovilRepository automovilRepository;
    @Mock
    private CamionRepository camionRepository;
    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private VehiculoService vehiculoService;

    @Test
    void getVehiculos_deberiaRetornarListaCombinada() {
        Automovil auto = new Automovil();
        auto.setVehiculo(new Vehiculo(1L, "Ford", "Fiesta", "ABC123", LocalDate.of(2020, 1, 1), 50000, "1.6"));

        Camion camion = new Camion();
        camion.setVehiculo(new Vehiculo(2L,"Mercedes", "Atego", "CAM456", LocalDate.of(2018, 5, 10), 200000, "5.0"));

        when(automovilRepository.findAll()).thenReturn(List.of(auto));
        when(camionRepository.findAll()).thenReturn(List.of(camion));

        List<VehiculoResponse> result = vehiculoService.getVehiculos();

        assertEquals(2, result.size());
    }

    @Test
    void getVehiculoByPatente_deberiaRetornarAutomovil() {
        Vehiculo vehiculo = new Vehiculo(1L, "Ford", "Fiesta", "ABC123", LocalDate.of(2020, 1, 1), 50000, "1.6");
        Automovil automovil = new Automovil(null, "SEDAN", 4, 5, 1, vehiculo);

        when(vehiculoRepository.findByPatente("ABC123")).thenReturn(Optional.of(vehiculo));
        when(automovilRepository.findByVehiculoId(1L)).thenReturn(Optional.of(automovil));

        VehiculoResponse response = vehiculoService.getVehiculoByPatente("ABC123");

        assertTrue(response instanceof AutomovilResponse);
        assertEquals("Ford", response.getMarca());
    }

    @Test
    void createVehiculo_deberiaCrearYRetornarAutomovil() {
        AutomovilResponse input = new AutomovilResponse();
        input.setMarca("Ford");
        input.setModelo("Focus");
        input.setPatente("FOC123");
        input.setAnioFabricacion(LocalDate.of(2019, 6, 1));
        input.setKilometraje(30000);
        input.setCilindrada("1.6");
        input.setTipo("HATCHBACK");
        input.setCantPuertas(5);
        input.setCantPasajeros(5);
        input.setCantMaletero(1);

        Vehiculo saved = new Vehiculo(1L, "Ford", "Focus", "FOC123", LocalDate.of(2019, 6, 1), 30000, "1.6");

        when(vehiculoRepository.findByPatente("FOC123")).thenReturn(Optional.empty());
        when(vehiculoRepository.save(any())).thenReturn(saved);
        when(automovilRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        VehiculoResponse response = vehiculoService.createVehiculo(input);

        assertTrue(response instanceof AutomovilResponse);
        assertEquals("FOC123", response.getPatente());
    }

    @Test
    void updateVehiculo_deberiaActualizarYRetornarCamion() {
        CamionResponse input = new CamionResponse();
        input.setMarca("Volvo");
        input.setModelo("FH");
        input.setPatente("VOL123");
        input.setAnioFabricacion(LocalDate.of(2015, 3, 15));
        input.setKilometraje(150000);
        input.setCilindrada("12.0");
        input.setTipo("TRACTOR");
        input.setCantEjes(3);
        input.setCapacidadToneladas(25);

        Vehiculo vehiculo = new Vehiculo(1L, "Volvo", "FH", "VOL123", LocalDate.of(2015, 3, 15), 150000, "12.0");
        Camion camion = new Camion(1L, "TRACTOR", 25, 3, vehiculo);

        when(vehiculoRepository.findByPatente("VOL123")).thenReturn(Optional.of(vehiculo));
        when(vehiculoRepository.save(any())).thenReturn(vehiculo);
        when(camionRepository.findByVehiculoId(1L)).thenReturn(Optional.of(camion));
        when(camionRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        VehiculoResponse response = vehiculoService.updateVehiculo("VOL123", input);

        assertTrue(response instanceof CamionResponse);
        assertEquals("TRACTOR", ((CamionResponse) response).getTipo());
    }

    @Test
    void deleteVehiculo_deberiaEliminarCorrectamente() {
        Vehiculo vehiculo = new Vehiculo(1L, "Fiat", "Ducato", "FIAT123", LocalDate.of(2010, 7, 1), 250000, "2.3");

        when(vehiculoRepository.findByPatente("FIAT123")).thenReturn(Optional.of(vehiculo));
        when(automovilRepository.findByVehiculoId(1L)).thenReturn(Optional.empty());
        when(camionRepository.findByVehiculoId(1L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> vehiculoService.deleteVehiculo("FIAT123"));

        verify(vehiculoRepository).delete(vehiculo);
    }
}
