package com.example.project1.Repository;

import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralOrganizationRepository extends CrudRepository<GeneralOrganization, Long> {
    Optional<GeneralOrganization> findGeneralOrganizationByName(String name);
    Optional<GeneralOrganization> findGeneralOrganizationById(Long id);
    Optional<GeneralOrganization> findGeneralOrganizationByManager(User user);

}
