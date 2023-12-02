package br.com.clocktimeapi.clocktimeapi.modules.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;

@Service
public class EmployeeCreateService {

    @Autowired
    private EmployeeRepository employeeRepository;  

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        this.employeeRepository
            .findByUid(employeeEntity.getUid())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        return this.employeeRepository.save(employeeEntity);
    }

}
