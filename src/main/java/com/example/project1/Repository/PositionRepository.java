package com.example.project1.Repository;

import com.example.project1.Domain.Dictionary.DPosition;
import com.example.project1.Domain.Dictionary.DRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<DPosition,Long> {
    Optional<DPosition> findById(Long id);
    Optional<DPosition> findByPosition(String name);
}
