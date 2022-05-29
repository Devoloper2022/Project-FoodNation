package com.example.project1.Repository;

import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {
    Optional<Token> findById(Long id);
}
