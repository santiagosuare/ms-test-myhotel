package com.ms_test_myhotel.service;

import com.ms_test_myhotel.model.response.*;
import com.ms_test_myhotel.projection.*;
import com.ms_test_myhotel.repository.SalarioSegmentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmpleadoService {

    @Autowired
    private SalarioSegmentoRepository salarioSegmentoRepository;

    public List<SalarioSegmentoResponse> getEmpleadosPorSegmentoSueldos() {
        log.info("Obteniendo cantidad de empleados dentro de los siguientes segmentos de sueldos");
        try {
            List<SalarioSegmentoResponse> salarioSegmentoResponses = getSalarioSegmentoResponses();
            log.info("Cantidad de empleados por segmento de sueldos obtenida con exito");
            return salarioSegmentoResponses;
        } catch (Exception e) {
            log.error("Error al obtener la cantidad de empleados por segmento de sueldos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener la cantidad de empleados por segmento de sueldos");
        }
    }

    private List<SalarioSegmentoResponse> getSalarioSegmentoResponses() {
        List<SalarioSegmento> salarioSegmentos = salarioSegmentoRepository.getSegments();
        List<SalarioSegmentoResponse> salarioSegmentoResponses = new ArrayList<>();
        for (SalarioSegmento salarioSegmento : salarioSegmentos) {
            SalarioSegmentoResponse response = new SalarioSegmentoResponse();
            response.setSegmento(salarioSegmento.getSegmento());
            response.setCantidadEmpleados(salarioSegmento.getCantidadEmpleados());
            salarioSegmentoResponses.add(response);
        }
        return salarioSegmentoResponses;
    }

    public List<SalarioSegmentoDepartamentoResponse> getSegmentoSueldoDepartamento(){
        log.info("Obteniendo segmentos de sueldo, pero por departamento");
        try{
            List<SalarioSegmentoDepartamentoResponse> salarioSegmentoDepartamentoResponses = getSalarioSegmentoDepartamentoResponses();
            log.info("Cantidad de empleados por segmento de sueldos obtenida con exito");
            return salarioSegmentoDepartamentoResponses;
        } catch (Exception e){
            log.error("Error al obtener la cantidad de empleados por segmento de sueldos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener la cantidad de empleados por segmento de sueldos");
        }
    }

    private List<SalarioSegmentoDepartamentoResponse> getSalarioSegmentoDepartamentoResponses() {
        List<SalarioSegmentoDepartamento> salarioSegmentoDepartamentos = salarioSegmentoRepository.getSegmentsByDeparmanet();
        List<SalarioSegmentoDepartamentoResponse> salarioSegmentoDepartamentoResponses = new ArrayList<>();
        for (SalarioSegmentoDepartamento salarioSegmentoDepartamento : salarioSegmentoDepartamentos) {
            SalarioSegmentoDepartamentoResponse response = new SalarioSegmentoDepartamentoResponse();
            response.setDepartamentoId(salarioSegmentoDepartamento.getDepartamentoId());
            response.setDepartamentoNombre(salarioSegmentoDepartamento.getDepartamentoNombre());
            response.setSegmento(salarioSegmentoDepartamento.getSegmento());
            response.setCantidadEmpleados(salarioSegmentoDepartamento.getCantidadEmpleados());
            salarioSegmentoDepartamentoResponses.add(response);
        }
        return salarioSegmentoDepartamentoResponses;
    }

    public List<EmpleadoSalarioResponse> getMayorSueldoEmpleado(){
        log.info("Obteniendo el empleado con el mayor sueldo por departamento");
        try{
            List<EmpleadoSalarioResponse> empleadoSalarioResponses = getEmpleadoSalarioResponses();
            log.info("Empleado con el mayor sueldo obtenido con exito");
            return empleadoSalarioResponses;
        } catch (Exception e){
            log.error("Error al obtener el empleado con el mayor sueldo: {}", e.getMessage());
            throw new RuntimeException("Error al obtener el empleado con el mayor sueldo");
        }
    }

    private List<EmpleadoSalarioResponse> getEmpleadoSalarioResponses() {
        List<EmpleadoSalario> empleadoSalarios = salarioSegmentoRepository.getMayorSueldoEmpleado();
        List<EmpleadoSalarioResponse> empleadoSalarioResponses = new ArrayList<>();
        empleadoSalarios.forEach( empleadoSalario -> {
            EmpleadoSalarioResponse response = new EmpleadoSalarioResponse();
            response.setDepartamentoId(empleadoSalario.getDepartamentoId());
            response.setDepartamentoName(empleadoSalario.getDepartamentoName());
            response.setEmployeeId(empleadoSalario.getEmployeeId());
            response.setFirstName(empleadoSalario.getFirstName());
            response.setLastName(empleadoSalario.getLastName());
            response.setSalary(empleadoSalario.getSalary());
            empleadoSalarioResponses.add(response);
        });
        return empleadoSalarioResponses;
    }

    public List<EmpleadoGerenteTiempoResponse> getGerentesTiempo(){
        log.info("Obteniendo gerentes con tiempo en la compañia");
        try {
            List<EmpleadoGerenteTiempo> empleadoGerenteTiempos = salarioSegmentoRepository.getGerentesTiempo();
            List<EmpleadoGerenteTiempoResponse> empleadoGerenteTiempoResponses = new ArrayList<>();
            empleadoGerenteTiempos.forEach( empleadoGerenteTiempo -> {
                EmpleadoGerenteTiempoResponse response = new EmpleadoGerenteTiempoResponse();
                response.setEmployeeId(empleadoGerenteTiempo.getEmployeeId());
                response.setFirstName(empleadoGerenteTiempo.getFirstName());
                response.setLastName(empleadoGerenteTiempo.getLastName());
                response.setHireDate(empleadoGerenteTiempo.getHireDate());
                response.setJobTitle(empleadoGerenteTiempo.getJobTitle());
                empleadoGerenteTiempoResponses.add(response);
            });
            log.info("Gerentes con tiempo en la compañia obtenidos con exito");
            return empleadoGerenteTiempoResponses;
        } catch (Exception e) {
            log.error("Error al obtener los gerentes con tiempo en la compañia: {}", e.getMessage());
            throw new RuntimeException("Error al obtener los gerentes con tiempo en la compañia");
        }
    }

    public List<SalarioPromedioResponse> getSalarioPromedioPorDepartamento(){
        log.info("Obteniendo salario promedio por departamento");
        try {
            List<SalarioPromedio> salarioPromedios = salarioSegmentoRepository.getSalarioPromedioPorDepartamento();
            List<SalarioPromedioResponse> salarioPromedioResponses = new ArrayList<>();
            salarioPromedios.forEach( salarioPromedio -> {
                SalarioPromedioResponse response = new SalarioPromedioResponse();
                response.setDepartamentoId(salarioPromedio.getDepartamentoId());
                response.setDepartamentoName(salarioPromedio.getDepartamentoName());
                response.setSalarioPromedio(salarioPromedio.getSalarioPromedio());
                response.setCantidadEmpleados(salarioPromedio.getCantidadEmpleados());
                salarioPromedioResponses.add(response);
            });
            log.info("Salario promedio por departamento obtenido con exito");
            return salarioPromedioResponses;
        } catch (Exception e) {
            log.error("Error al obtener el salario promedio por departamento: {}", e.getMessage());
            throw new RuntimeException("Error al obtener el salario promedio por departamento");
        }
    }

    public List<EmpleadoPaisResponse> getEmpleadosPorPais(){
        log.info("Obteniendo empleados por pais");
        try{
            List<EmpleadoPais> empleadoPaises = salarioSegmentoRepository.getEmpleadosPorPais();
            List<EmpleadoPaisResponse> empleadoPaisResponses = new ArrayList<>();
            empleadoPaises.forEach( empleadoPais -> {
                EmpleadoPaisResponse response = new EmpleadoPaisResponse();
                response.setPais(empleadoPais.getPais());
                response.setCantidadEmpleados(empleadoPais.getCantidadEmpleados());
                response.setSalarioPromedio(empleadoPais.getSalarioPromedio());
                response.setSalarioMaximo(empleadoPais.getSalarioMaximo());
                response.setSalarioMinimo(empleadoPais.getSalarioMinimo());
                response.setPromedioAntiguedadAnios(empleadoPais.getPromedioAntiguedadAnios());
                empleadoPaisResponses.add(response);
            });
            log.info("Empleados por pais obtenidos con exito");
            return empleadoPaisResponses;
        } catch (Exception e){
            log.error("Error al obtener los empleados por pais: {}", e.getMessage());
            throw new RuntimeException("Error al obtener los empleados por pais");
        }
    }
}
