package com.example.project1.Repository;

import com.example.project1.Domain.LocalOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalOrganizationRepository extends CrudRepository<LocalOrganization, Long> {
    LocalOrganization findLocalOrganizationByName(String name);

    LocalOrganization findLocalOrganizationById(Long id);
}
