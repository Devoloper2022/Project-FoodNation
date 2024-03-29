package com.example.project1.Repository;


import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> findAllByGeneralOrganization(GeneralOrganization gen);

    List<User> findAllByLocalOrganization(LocalOrganization local);
}
