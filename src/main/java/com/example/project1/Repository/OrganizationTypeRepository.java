package com.example.project1.Repository;

import com.example.project1.Domain.Dictionary.DOrganizationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationTypeRepository extends CrudRepository<DOrganizationType,Long> {
}
