package br.com.clocktimeapi.clocktimeapi.modules.employee.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
    Optional<EmployeeEntity> findByNameOrEmailOrUid(String name, String email, String uid);
    Optional<EmployeeEntity> findByUid(String uid);
    Optional<EmployeeEntity> findById(Integer id);
}
