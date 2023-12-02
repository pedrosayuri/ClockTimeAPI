package br.com.clocktimeapi.clocktimeapi.modules.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clocktimeapi.clocktimeapi.modules.user.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLoginAndPassword(String name, String password);
    Optional<UserEntity> findByEmployee(EmployeeEntity employee);
    Optional<UserEntity> findById(int id);
}