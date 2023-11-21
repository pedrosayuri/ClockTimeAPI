package br.com.clocktimeapi.clocktimeapi.modules.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clocktimeapi.clocktimeapi.modules.users.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserEntity> findByNomeOrEmailOrUid(String nome, String email, String uid);
    Optional<UserEntity> findByUid(String uid);
    Optional<UserEntity> findById(UUID id);
}
