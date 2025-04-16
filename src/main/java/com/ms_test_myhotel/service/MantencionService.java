package com.ms_test_myhotel.service;

import com.ms_test_myhotel.model.dto.Mantencion;
import com.ms_test_myhotel.model.dto.Vehiculo;
import com.ms_test_myhotel.repository.MantencionRepository;
import com.ms_test_myhotel.repository.VehiculoRepository;
import com.ms_test_myhotel.model.response.MantencionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MantencionService {

    @Autowired
    private MantencionRepository mantencionRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public MantencionResponse createMantencion(String patente, MantencionResponse mantencionResponse){
        log.info("Creando mantencion del vechiculo para la patente {}", patente);
        try{
            log.info("Buscando vehiculo por patente {}", patente);
            Optional<Vehiculo> vehiculoCheck = vehiculoRepository.findByPatente(patente);
            if (vehiculoCheck.isEmpty()){
                log.info("El vehiculo no existe");
                return null;
            }
            Mantencion mantencion = mapMantencion(mantencionResponse, vehiculoCheck.get());
            Mantencion mantencionSaved = mantencionRepository.save(mantencion);
            log.info("Mantencion creada con exito para la patente {}", patente);
            return mapMantencionResponse(mantencionSaved);
        } catch (Exception e){
            log.error("Error al crear la mantencion del vechiculo para la patente {}: {}", patente, e.getMessage());
            throw new RuntimeException("Error al crear la mantencion");
        }

    }

    public List<MantencionResponse> getMantenciones(String patente){
        log.info("Obteniendo mantenciones del vechiculo para la patente {}", patente);
        try{
            log.info("Buscando vehiculo por patente {}", patente);
            Optional<Vehiculo> vehiculoCheck = vehiculoRepository.findByPatente(patente);
            if (vehiculoCheck.isEmpty()){
                log.info("El vehiculo no existe");
                return null;
            }

            List<Mantencion> mantencionList = mantencionRepository.findByPatente(patente);

            if (mantencionList.isEmpty()){
                log.info("No se encontraron mantenciones para la patente {}", patente);
                return null;
            }

            List<MantencionResponse> mantencionResponseList = mantencionList.stream()
                    .map(this::mapMantencionResponse)
                    .toList();

            log.info("Mantenciones obtenidas con exito para la patente {}", patente);

            return mantencionResponseList;
        } catch (Exception e){
            log.error("Error al obtener las mantenciones del vechiculo para la patente {}: {}", patente, e.getMessage());
            throw new RuntimeException("Error al obtener las mantenciones");
        }
    }

    public void deleteMantencion(String patente, Long id){
        log.info("Eliminando mantencion del vechiculo para la patente {}", patente);
        try{
            log.info("Buscando vehiculo por patente {}", patente);
            Optional<Vehiculo> vehiculoCheck = vehiculoRepository.findByPatente(patente);
            if (vehiculoCheck.isEmpty()){
                log.info("El vehiculo no existe");
            }

            Optional<Mantencion> mantencionCheck = mantencionRepository.findById(id);
            if (mantencionCheck.isEmpty()){
                log.info("La mantencion no existe");
            }

            mantencionRepository.deleteById(id);
            log.info("Mantencion eliminada con exito para la patente {}", patente);
        } catch (Exception e){
            log.error("Error al eliminar la mantencion del vechiculo para la patente {}: {}", patente, e.getMessage());
            throw new RuntimeException("Error al eliminar la mantencion");
        }
    }

    private Mantencion mapMantencion(MantencionResponse mantencionResponse, Vehiculo vehiculo) {
        Mantencion mantencion = new Mantencion();
        mantencion.setFechaMantencion(LocalDate.now());
        mantencion.setCosto(mantencionResponse.getCosto());
        mantencion.setDescripcion(mantencionResponse.getDescripcion());
        mantencion.setVehiculo(vehiculo);
        return mantencion;
    }

    private MantencionResponse mapMantencionResponse(Mantencion mantencion) {
        MantencionResponse mantencionResponse = new MantencionResponse();
        mantencionResponse.setId(mantencion.getId());
        mantencionResponse.setFechaMantencion(mantencion.getFechaMantencion());
        mantencionResponse.setDescripcion(mantencion.getDescripcion());
        mantencionResponse.setCosto(mantencion.getCosto());
        mantencionResponse.setPatente(mantencion.getVehiculo().getPatente());
        return mantencionResponse;
    }
}
