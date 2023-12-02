package br.com.clocktimeapi.clocktimeapi.modules.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.employee.dto.EmployeeReadDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;

@Service
public class EmployeeReadService {

    @Autowired
    private EmployeeRepository userRepository;

    public EmployeeReadDTO read(String uid) {
        EmployeeEntity user = userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));

        EmployeeReadDTO userReadDTO = new EmployeeReadDTO(
            user.getName(),
            user.getEmail());

        return userReadDTO;
    }
    
}
