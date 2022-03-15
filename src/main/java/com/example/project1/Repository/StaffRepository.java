package com.example.project1.Repository;


import com.example.project1.Domain.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Optional<Staff> findStaffByUsername(String username);
    Optional<Staff> findStaffByEmail(String email);
    Optional<Staff> findStaffById(Long id);
}
