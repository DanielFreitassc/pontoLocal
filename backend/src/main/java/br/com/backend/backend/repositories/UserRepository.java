package br.com.backend.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.backend.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    UserEntity findByCpf(String cpf);
}
