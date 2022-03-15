package com.example.project1.Services;

import com.example.project1.CustomTemp.exceptions.StaffExistException;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.Staff;
import com.example.project1.Repository.LocalOrganizationRepository;
import com.example.project1.Repository.StaffRepository;
import com.example.project1.Security.Payload.request.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {
    public static final Logger LOG = LoggerFactory.getLogger(StaffService.class);

    private final StaffRepository staffRepository;
    private final LocalOrganizationRepository localOrganizationRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StaffService(StaffRepository staffRepository, LocalOrganizationRepository localOrganizationRepository, BCryptPasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.localOrganizationRepository = localOrganizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Staff creatStaff(SignUpRequest userIn) {
        Staff staff = new Staff();
//        Optional<LocalOrganization> findLocalOrganization = localOrganizationRepository.findById(userIn.getOrganizationId());
//        LocalOrganization localOrganizationID=findLocalOrganization.get();

        staff.setFirstName(userIn.getFirstName());
        staff.setSecondName(userIn.getSecondName());
        staff.setUsername(userIn.getUsername());
        staff.setPassword(passwordEncoder.encode(userIn.getPassword()));
        staff.setEmail(userIn.getEmail());
        staff.setPhoneNumber(userIn.getPhoneNumber());
//        staff.setLocalOrganization(localOrganization);

        try {
            LOG.info("Save user {} ", userIn.getEmail());
            return staffRepository.save(staff);
        } catch (Exception ex) {
            LOG.error("Error during registration", ex.getMessage());
            throw new StaffExistException("The user " + userIn.getUsername() + " already exist.Please check your credentials");
        }
    }
}
