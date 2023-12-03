package br.com.clocktimeapi.clocktimeapi.modules.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;

@Service
public class EmployeeUpdateService {
    
    @Autowired
    private EmployeeRepository userRepository;
    
    public EmployeeEntity update(String uid, EmployeeEntity employeeEntity) {
        var user = this.userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));

        user.setName(employeeEntity.getName());
        user.setEmail(employeeEntity.getEmail());
        user.setUid(employeeEntity.getUid());

        return this.userRepository.save(user);
    }

}
