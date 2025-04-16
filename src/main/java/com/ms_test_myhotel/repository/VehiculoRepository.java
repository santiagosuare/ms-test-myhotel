package com.ms_test_myhotel.repository;

import com.ms_test_myhotel.model.dto.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    @Query("SELECT v FROM Vehiculo v WHERE v.patente = ?1")
    Optional<Vehiculo> findByPatente(String patente);
}
