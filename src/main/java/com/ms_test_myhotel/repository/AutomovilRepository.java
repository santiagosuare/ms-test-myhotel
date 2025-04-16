package com.ms_test_myhotel.repository;

import com.ms_test_myhotel.model.dto.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutomovilRepository extends JpaRepository<Automovil, Long> {

    @Query("SELECT a FROM Automovil a WHERE a.vehiculo.id = ?1")
    Optional<Automovil> findByVehiculoId(Long vehiculoId);
}
