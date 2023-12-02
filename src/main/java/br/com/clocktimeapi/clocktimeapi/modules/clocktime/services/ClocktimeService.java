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
    private EmployeeRepository userRepository;

    @Autowired
    private AuthClocktimeService authLoginService;

    public ClocktimeResponseDTO login(ClocktimeRequestDTO loginRequestDTO) throws AuthenticationException {
        EmployeeEntity user = userRepository.findByUid(loginRequestDTO.getLogin())
                .orElseThrow(() -> new UserNotFoundException(loginRequestDTO.getLogin()));

        AuthClocktimeRequestDTO authLoginRequestDTO = new AuthClocktimeRequestDTO(user.getUid());
        String token = authLoginService.createJWTTokenLogin(authLoginRequestDTO).getAccess_token();
        return new ClocktimeResponseDTO(token);

    }

}
