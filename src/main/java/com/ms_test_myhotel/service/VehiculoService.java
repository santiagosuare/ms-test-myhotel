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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VehiculoService {

    @Autowired
    private AutomovilRepository automovilRepository;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<VehiculoResponse> getVehiculos() {
        log.info("Buscando los vehiculos...");
        try{
            List<Automovil> automoviles = automovilRepository.findAll();
            List<Camion> camiones = camionRepository.findAll();

            List<VehiculoResponse> vehiculoResponses = new ArrayList<>();

            automoviles.forEach( auto -> {
                AutomovilResponse automovilResponse = createAutomovilResponse(auto);
                vehiculoResponses.add(automovilResponse);
            });

            camiones.forEach( camion -> {
                CamionResponse camionResponse = createCamionResponse(camion);
                vehiculoResponses.add(camionResponse);
            });

            log.info("Vehiculos encontrados: {}", vehiculoResponses.size());
            return vehiculoResponses;

        } catch (Exception e){
            log.error("Error retrieving vehiculos", e);
            return new ArrayList<>();
        }
    }

    public VehiculoResponse getVehiculoByPatente(String patente){
        log.info("Buscando vehiculo por patente...");
        try {
            Optional<Vehiculo> vehiculo = vehiculoRepository.findByPatente(patente);
            if (vehiculo.isPresent()) {
                Vehiculo vehiculoFound = vehiculo.get();

                log.info("Vehiculo encontrado: {}", vehiculoFound);

                Optional<Automovil> automovilFound = automovilRepository.findByVehiculoId(vehiculoFound.getId());
                if (automovilFound.isPresent()) {
                    return createAutomovilResponse(automovilFound.get());
                }
                Optional<Camion> camionFound = camionRepository.findByVehiculoId(vehiculoFound.getId());
                if (camionFound.isPresent()) {
                    return createCamionResponse(camionFound.get());
                }
            }

            log.info("Vehiculo no encontrado");
            return null;

        } catch (Exception e) {
            log.error("Error al buscar vehiculo por patente", e);
            return null;
        }
    }

    public VehiculoResponse createVehiculo(VehiculoResponse vehiculoResponse) {

        log.info("Verifico si el vehiculo ya existe...");
        Optional<Vehiculo> vehiculoCheck = vehiculoRepository.findByPatente(vehiculoResponse.getPatente());
        if (vehiculoCheck.isPresent()){
            log.info("El vehiculo ya existe");
            return null;
        }

        log.info("Creando vehiculo...");
        try{
            Vehiculo vehiculo = mapVehiculo(vehiculoResponse);
            Vehiculo vehiculoSaved = vehiculoRepository.save(vehiculo);

            if (vehiculoResponse instanceof AutomovilResponse){
                Automovil automovil = mapAutomovil(vehiculoResponse, vehiculoSaved);
                Automovil automovilSaved = automovilRepository.save(automovil);
                return createAutomovilResponse(automovilSaved);

            } else if (vehiculoResponse instanceof CamionResponse){
                Camion camion = mapCamion(vehiculoResponse, vehiculoSaved);
                Camion camionSaved = camionRepository.save(camion);
                return createCamionResponse(camionSaved);

            } else {
                log.error("Tipo de vehiculo no soportado");
                return null;

            }
        } catch (Exception e){
            log.error("Error al crear vehiculo", e);
            return null;
        }
    }

    public void deleteVehiculo(String patente) {
        log.info("Buscando vehiculo por patente...");
        try {
            Optional<Vehiculo> vehiculo = vehiculoRepository.findByPatente(patente);
            if (vehiculo.isPresent()) {
                Vehiculo vehiculoFound = vehiculo.get();

                Optional<Automovil> automovilFound = automovilRepository.findByVehiculoId(vehiculoFound.getId());
                automovilFound.ifPresent(automovil -> automovilRepository.delete(automovil));

                Optional<Camion> camionFound = camionRepository.findByVehiculoId(vehiculoFound.getId());
                camionFound.ifPresent(camion -> camionRepository.delete(camion));

                vehiculoRepository.delete(vehiculoFound);
                log.info("Vehiculo eliminado: {}", vehiculoFound);
            } else {
                log.info("Vehiculo no encontrado");
            }
        } catch (Exception e) {
            log.error("Error al eliminar vehiculo por patente", e);
        }
    }

    public VehiculoResponse updateVehiculo(String patente, VehiculoResponse vehiculoResponse) {
        log.info("Buscando vehiculo por patente...");
        try {
            Optional<Vehiculo> vehiculo = vehiculoRepository.findByPatente(patente);
            if (vehiculo.isPresent()) {
                Vehiculo vehiculoFound = vehiculo.get();
                vehiculoFound.setMarca(vehiculoResponse.getMarca());
                vehiculoFound.setModelo(vehiculoResponse.getModelo());
                vehiculoFound.setPatente(vehiculoResponse.getPatente());
                vehiculoFound.setAnioFabricacion(vehiculoResponse.getAnioFabricacion());
                vehiculoFound.setKilometraje(vehiculoResponse.getKilometraje());
                vehiculoFound.setCilindrada(vehiculoResponse.getCilindrada());

                Vehiculo vehiculoSaved = vehiculoRepository.save(vehiculoFound);

                if (vehiculoResponse instanceof AutomovilResponse){
                    Optional<Automovil> optAutomovil = automovilRepository.findByVehiculoId(vehiculoSaved.getId());
                    Automovil automovil = optAutomovil.orElse(new Automovil());
                    
                    automovil.setVehiculo(vehiculoSaved);
                    automovil.setTipo(((AutomovilResponse) vehiculoResponse).getTipo());
                    automovil.setCantPuertas(((AutomovilResponse) vehiculoResponse).getCantPuertas());
                    automovil.setCantPasajeros(((AutomovilResponse) vehiculoResponse).getCantPasajeros());
                    automovil.setCantMaletero(((AutomovilResponse) vehiculoResponse).getCantMaletero());

                    Automovil automovilSaved = automovilRepository.save(automovil);
                    return createAutomovilResponse(automovilSaved);

                } else if (vehiculoResponse instanceof CamionResponse){
                    Optional<Camion> optCamion = camionRepository.findByVehiculoId(vehiculoSaved.getId());
                    Camion camion = optCamion.orElse(new Camion());

                    camion.setVehiculo(vehiculoSaved);
                    camion.setTipo(((CamionResponse) vehiculoResponse).getTipo());
                    camion.setCapacidadToneladas(((CamionResponse) vehiculoResponse).getCapacidadToneladas());
                    camion.setCantEjes(((CamionResponse) vehiculoResponse).getCantEjes());

                    Camion camionSaved = camionRepository.save(camion);
                    return createCamionResponse(camionSaved);

                } else {
                    log.error("Tipo de vehiculo no soportado");
                    return null;

                }
            } else {
                log.info("Vehículo no encontrado");
                return null;
            }
        } catch (Exception e) {
            log.error("Error al actualizar vehículo", e);
            return null;
        }
    }

    private Automovil mapAutomovil(VehiculoResponse vehiculoResponse, Vehiculo vehiculoSaved){
        Automovil automovil = new Automovil();
        automovil.setVehiculo(vehiculoSaved);
        automovil.setTipo(((AutomovilResponse) vehiculoResponse).getTipo());
        automovil.setCantPuertas(((AutomovilResponse) vehiculoResponse).getCantPuertas());
        automovil.setCantPasajeros(((AutomovilResponse) vehiculoResponse).getCantPasajeros());
        automovil.setCantMaletero(((AutomovilResponse) vehiculoResponse).getCantMaletero());
        return automovil;
    }

    private Camion mapCamion(VehiculoResponse vehiculoResponse, Vehiculo vehiculoSaved){
        Camion camion = new Camion();
        camion.setVehiculo(vehiculoSaved);
        camion.setTipo(((CamionResponse) vehiculoResponse).getTipo());
        camion.setCapacidadToneladas(((CamionResponse) vehiculoResponse).getCapacidadToneladas());
        camion.setCantEjes(((CamionResponse) vehiculoResponse).getCantEjes());
        return camion;
    }

    private Vehiculo mapVehiculo(VehiculoResponse vehiculoResponse){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(vehiculoResponse.getMarca());
        vehiculo.setModelo(vehiculoResponse.getModelo());
        vehiculo.setPatente(vehiculoResponse.getPatente());
        vehiculo.setAnioFabricacion(vehiculoResponse.getAnioFabricacion());
        vehiculo.setKilometraje(vehiculoResponse.getKilometraje());
        vehiculo.setCilindrada(vehiculoResponse.getCilindrada());
        return vehiculo;
    }

    private CamionResponse createCamionResponse(Camion camion) {
        CamionResponse camionResponse = new CamionResponse();
        camionResponse.setMarca(camion.getVehiculo().getMarca());
        camionResponse.setModelo(camion.getVehiculo().getModelo());
        camionResponse.setPatente(camion.getVehiculo().getPatente());
        camionResponse.setAnioFabricacion(camion.getVehiculo().getAnioFabricacion());
        camionResponse.setKilometraje(camion.getVehiculo().getKilometraje());
        camionResponse.setCilindrada(camion.getVehiculo().getCilindrada());
        camionResponse.setTipo(camion.getTipo());
        camionResponse.setCapacidadToneladas(camion.getCapacidadToneladas());
        camionResponse.setCantEjes(camion.getCantEjes());

        return camionResponse;
    }

    private AutomovilResponse createAutomovilResponse(Automovil automovil) {
        AutomovilResponse automovilResponse = new AutomovilResponse();
        automovilResponse.setMarca(automovil.getVehiculo().getMarca());
        automovilResponse.setModelo(automovil.getVehiculo().getModelo());
        automovilResponse.setPatente(automovil.getVehiculo().getPatente());
        automovilResponse.setAnioFabricacion(automovil.getVehiculo().getAnioFabricacion());
        automovilResponse.setKilometraje(automovil.getVehiculo().getKilometraje());
        automovilResponse.setCilindrada(automovil.getVehiculo().getCilindrada());
        automovilResponse.setTipo(automovil.getTipo());
        automovilResponse.setCantPuertas(automovil.getCantPuertas());
        automovilResponse.setCantPasajeros(automovil.getCantPasajeros());
        automovilResponse.setCantMaletero(automovil.getCantMaletero());

        return automovilResponse;
    }
}
