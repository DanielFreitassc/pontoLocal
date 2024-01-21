package br.com.backend.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.backend.models.PontoEntity;

@Repository
public interface PontoRepository extends JpaRepository<PontoEntity,UUID>{
    
}
