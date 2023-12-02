package br.com.clocktimeapi.clocktimeapi.modules.login.services;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.AuthLoginRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.LoginRequestDTO;
import br.com.clocktimeapi.clocktimeapi.modules.login.dto.LoginResponseDTO;
import br.com.clocktimeapi.clocktimeapi.modules.user.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.user.repositories.UserRepository;

@Service
public class LoginService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthLoginService authLoginService;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationException {
        UserEntity user = userRepository.findByLoginAndPassword(loginRequestDTO.getLogin(), loginRequestDTO.getPassword())
                .orElseThrow(() -> new UserNotFoundException(loginRequestDTO.getLogin()));

        AuthLoginRequestDTO authLoginRequestDTO = new AuthLoginRequestDTO(user.getId());
        String token = authLoginService.createJWTTokenLogin(authLoginRequestDTO).getAccess_token();
        return new LoginResponseDTO(token);
    }

}
