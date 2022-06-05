package com.example.project1.Services;

import com.example.project1.CustomTemplate.exceptions.ExceptionText;
import com.example.project1.Domain.LocalOrganization;
import com.example.project1.Domain.Reservation;
import com.example.project1.Domain.User;
import com.example.project1.Repository.LocalOrganizationRepository;
import com.example.project1.Repository.OrderDetailsRepository;
import com.example.project1.Repository.ReservationRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.ReservationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class ReservationService {
    public static final Logger LOG = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetAilsRepository;
    private final LocalOrganizationRepository organizationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, OrderDetailsRepository orderDetAilsRepository, LocalOrganizationRepository organizationRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.orderDetAilsRepository = orderDetAilsRepository;
        this.organizationRepository = organizationRepository;
    }

    public Object create(ReservationDTO dto, Principal principal){
        LocalOrganization organization=organizationRepository.findLocalOrganizationById(dto.getOrgID()).get();

        Optional<String> checkTime=organization.getCheckTime()
                .stream()
                .filter(u->u.equals(dto.getDate())).findAny();

        if (checkTime.isPresent()){
            organization.getCheckTime().add(dto.getDate());
            organizationRepository.save(organization);

            User user =getUserByPrincipal(principal);
            Reservation reservation = new Reservation();
            reservation.setCustomer(user);
            reservation.setDate(dto.getDate());
            reservation.setPersons(Math.toIntExact(dto.getPersons()));
            reservation.setLocalOrganization(organization);
            reservation.setStatus(false);

            return reservationRepository.save(reservation);
        }else {
            return new ExceptionText("time is reserved");
        }
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }
}
