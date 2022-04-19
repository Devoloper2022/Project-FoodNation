package com.example.project1.Repository;

import com.example.project1.Domain.Dictionary.DRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<DRole, Long> {
    Optional<DRole> findById(Long id);
    Optional<DRole> findByRole(String name);
}
