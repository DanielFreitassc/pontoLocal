package br.com.backend.backend.controllers;

import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.backend.models.PontoEntity;
import br.com.backend.backend.models.UserEntity;
import br.com.backend.backend.repositories.PontoRepository;
import br.com.backend.backend.repositories.UserRepository;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PontoRepository pontoRepository;

    @PostMapping("/register") 
    public ResponseEntity create(@RequestBody UserEntity userEntity) {
        var user = this.userRepository.findByCpf(userEntity.getCpf());
        if(user != null){
            System.out.println("Usuário já cadastrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
        }
        var userCreated = this.userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity) {
        var existingUser = this.userRepository.findByCpf(userEntity.getCpf());
        if (existingUser != null) {
            System.out.println("Usuário encontrado");
    
           
            PontoEntity pontoEntity = new PontoEntity();
            
            pontoEntity.setName(existingUser.getName());
           
    
            var pontoCreated = this.pontoRepository.save(pontoEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Ponto marcado");
        }
    
        
    
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }
    
    @GetMapping("/ponto")
    public ResponseEntity<List<PontoEntity>> getPonto() {
    List<PontoEntity> pontoList = pontoRepository.findAll();
    
    if (!pontoList.isEmpty()) {
        for (PontoEntity ponto : pontoList) {
           
            long id = ponto.getId();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pontoList);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }
}

    

}

