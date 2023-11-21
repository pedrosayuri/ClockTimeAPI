package br.com.clocktimeapi.clocktimeapi.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;
import br.com.clocktimeapi.clocktimeapi.modules.users.repositories.UserRepository;

@Service
public class UserUpdateService {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserEntity update(String uid, UserEntity userEntity) {
        var user = this.userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));

        user.setNome(userEntity.getNome());
        user.setEmail(userEntity.getEmail());
        user.setValor_hora(userEntity.getValor_hora());
        user.setData_admissao(userEntity.getData_admissao());
        user.setUid(userEntity.getUid());

        return this.userRepository.save(user);
    }

}
