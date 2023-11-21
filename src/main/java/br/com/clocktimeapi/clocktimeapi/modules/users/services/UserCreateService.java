package br.com.clocktimeapi.clocktimeapi.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.repositories.UserRepository;

@Service
public class UserCreateService {
 
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(UserEntity userEntity) {
        this.userRepository
            .findByUid(userEntity.getUid())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        return this.userRepository.save(userEntity);
    }

}
