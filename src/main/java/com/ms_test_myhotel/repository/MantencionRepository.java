package com.ms_test_myhotel.repository;

import com.ms_test_myhotel.model.dto.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantencionRepository extends JpaRepository<Mantencion, Long> {
    @Query("SELECT m FROM Mantencion m WHERE m.vehiculo.patente = ?1")
    List<Mantencion> findByPatente(String patente);
}
