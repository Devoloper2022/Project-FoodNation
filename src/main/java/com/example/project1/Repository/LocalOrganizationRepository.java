package com.example.project1.Repository;

import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.LocalOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalOrganizationRepository extends CrudRepository<LocalOrganization, Long> {
    Optional<LocalOrganization> findLocalOrganizationByName(String name);

    Optional<LocalOrganization> findLocalOrganizationById(Long id);

    List<LocalOrganization> findByGeneralOrganization(GeneralOrganization genOrg);
}
