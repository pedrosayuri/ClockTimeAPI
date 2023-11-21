package br.com.clocktimeapi.clocktimeapi.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;

import br.com.clocktimeapi.clocktimeapi.modules.users.dto.UserReadDTO;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.repositories.UserRepository;

@Service
public class UserReadService {

    @Autowired
    private UserRepository userRepository;

    public UserReadDTO read(String uid) {
        UserEntity user = userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));

        UserReadDTO userReadDTO = new UserReadDTO(
            user.getNome(),
            user.getEmail());

        return userReadDTO;
    }
    
}
