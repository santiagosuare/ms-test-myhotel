package com.ms_test_myhotel.repository;

import com.ms_test_myhotel.model.dto.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long>{
    @Query("SELECT c FROM Camion c WHERE c.vehiculo.id = ?1")
    Optional<Camion> findByVehiculoId(Long vehiculoId);
}
