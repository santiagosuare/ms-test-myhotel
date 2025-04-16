package com.ms_test_myhotel.repository;

import com.ms_test_myhotel.projection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalarioSegmentoRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SalarioSegmento> getSegments() {
        String sql = """
            SELECT
                CASE
                    WHEN salary < 3500 THEN 'SEGMENTO A'
                    WHEN salary >= 3500 AND salary < 8000 THEN 'SEGMENTO B'
                    ELSE 'SEGMENTO C'
                END AS segmento,
                COUNT(*) AS cantidad_empleados
            FROM employees
            GROUP BY
                CASE
                    WHEN salary < 3500 THEN 'SEGMENTO A'
                    WHEN salary >= 3500 AND salary < 8000 THEN 'SEGMENTO B'
                    ELSE 'SEGMENTO C'
                END
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new SalarioSegmento(
                rs.getString("segmento"),
                rs.getInt("cantidad_empleados")
        ));
    }

    public List<SalarioSegmentoDepartamento> getSegmentsByDeparmanet(){
        String sql = """
                SELECT
                    d.department_id,
                    d.department_name,
                    CASE
                        WHEN e.salary < 3500 THEN 'SEGMENTO A'
                        WHEN e.salary >= 3500 AND e.salary < 8000 THEN 'SEGMENTO B'
                        ELSE 'SEGMENTO C'
                    END AS segmento,
                    COUNT(*) AS cantidad_empleados
                FROM employees e
                JOIN departments d
                ON e.department_id = d.department_id
                GROUP BY
                    d.department_id,
                    d.department_name,
                    CASE
                        WHEN e.salary < 3500 THEN 'SEGMENTO A'
                        WHEN e.salary >= 3500 AND e.salary < 8000 THEN 'SEGMENTO B'
                        ELSE 'SEGMENTO C'
                    END
                ORDER BY
                    d.department_id,
                    segmento;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new SalarioSegmentoDepartamento(
                rs.getLong("department_id"),
                rs.getString("department_name"),
                rs.getString("segmento"),
                rs.getInt("cantidad_empleados")
        ));
    }

    public List<EmpleadoSalario> getMayorSueldoEmpleado(){
        String sql = """
                SELECT
                    d.department_id,
                    d.department_name,
                    e.employee_id,
                    e.first_name,
                    e.last_name,
                    e.salary
                FROM employees e
                JOIN departments d
                    ON e.department_id = d.department_id
                JOIN (
                    SELECT department_id, MAX(salary) AS salario_maximo
                    FROM employees
                    GROUP BY department_id
                ) AS sub
                    ON e.department_id = sub.department_id
                    AND e.salary = sub.salario_maximo
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new EmpleadoSalario(
                rs.getLong("department_id"),
                rs.getString("department_name"),
                rs.getLong("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("salary")
        ));
    }

    public List<EmpleadoGerenteTiempo> getGerentesTiempo(){
        String sql = """
                SELECT
                    e.employee_id,
                    e.first_name,
                    e.last_name,
                    e.hire_date,
                    j.job_title
                FROM employees e
                JOIN jobs j ON e.job_id = j.job_id
                WHERE j.job_title LIKE '%Manager%'
                  AND e.hire_date <= DATEADD('YEAR', -15, CURRENT_DATE)
                ORDER BY e.hire_date;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new EmpleadoGerenteTiempo(
                rs.getLong("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("hire_date"),
                rs.getString("job_title")
        ));
    }

    public List<SalarioPromedio> getSalarioPromedioPorDepartamento(){
        String sql = """
                SELECT
                    e.DEPARTMENT_ID,
                    d.DEPARTMENT_NAME,
                    ROUND(AVG(e.SALARY), 2) AS salario_promedio,
                    COUNT(*) AS cantidad_empleados
                FROM employees e
                JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
                GROUP BY e.DEPARTMENT_ID, d.DEPARTMENT_NAME
                HAVING COUNT(*) > 10;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new SalarioPromedio(
                rs.getLong("DEPARTMENT_ID"),
                rs.getString("DEPARTMENT_NAME"),
                rs.getDouble("salario_promedio"),
                rs.getInt("cantidad_empleados")
        ));
    }

    public List<EmpleadoPais> getEmpleadosPorPais(){
        String sql = """
                SELECT
                    c.COUNTRY_NAME AS pais,
                    COUNT(e.EMPLOYEE_ID) AS cantidad_empleados,
                    ROUND(AVG(e.SALARY), 2) AS salario_promedio,
                    MAX(e.SALARY) AS salario_maximo,
                    MIN(e.SALARY) AS salario_minimo,
                    ROUND(AVG(DATEDIFF('DAY', e.HIRE_DATE, CURRENT_DATE) / 365.0), 2) AS promedio_antiguedad_anios
                FROM employees e
                JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
                JOIN locations l ON d.LOCATION_ID = l.LOCATION_ID
                JOIN countries c ON l.COUNTRY_ID = c.COUNTRY_ID
                GROUP BY c.COUNTRY_NAME;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new EmpleadoPais(
                rs.getString("pais"),
                rs.getInt("cantidad_empleados"),
                rs.getDouble("salario_promedio"),
                rs.getDouble("salario_maximo"),
                rs.getDouble("salario_minimo"),
                rs.getDouble("promedio_antiguedad_anios")
        ));
    }
}
