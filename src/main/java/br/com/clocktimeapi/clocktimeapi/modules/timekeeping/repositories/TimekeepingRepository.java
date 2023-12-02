package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;

public interface TimekeepingRepository extends JpaRepository<TimekeepingEntity, Integer> {
    Optional<TimekeepingEntity> findByEmployee_id(Integer employee_id);
}
