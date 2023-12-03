package br.com.clocktimeapi.clocktimeapi.modules.clocktime.services;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.AuthClocktimeRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.ClocktimeRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.clocktime.dto.ClocktimeResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import br.com.clocktimeapi.clocktimeapi.modules.employee.repositories.EmployeeRepository;

@Service
public class ClocktimeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthClocktimeService authClocktimeService;

    public ClocktimeResponseDTO login(ClocktimeRequestDTO clocktimeRequestDTO) throws AuthenticationException {
        EmployeeEntity employeeEntity = employeeRepository.findByUid(clocktimeRequestDTO.getUid_access())
                .orElseThrow(() -> new UserNotFoundException(clocktimeRequestDTO.getUid_access()));

        AuthClocktimeRequestDTO authClocktimeRequestDTO = new AuthClocktimeRequestDTO(employeeEntity.getUid());
        String token = authClocktimeService.createJWTTokenLogin(authClocktimeRequestDTO).getAccess_token();
        return new ClocktimeResponseDTO(token);

    }

}
