package br.com.clocktimeapi.clocktimeapi.modules.payroll.repositories;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.payroll.entities.PayrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<PayrollEntity, Integer> {
    List<PayrollEntity> findByEmployee(EmployeeEntity employee);
}
