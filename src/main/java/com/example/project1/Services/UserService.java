package com.example.project1.Services;

import com.example.project1.CustomTemplate.exceptions.UserExistException;
import com.example.project1.Domain.Dictionary.DRole;
import com.example.project1.Domain.User;
import com.example.project1.Repository.RoleRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.CustomTemplate.Payload.request.SignUpRequest;
import com.example.project1.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User creatUser(SignUpRequest userIn) {

        User user = new User();
        user.setFirstName(userIn.getFirstName());
        user.setSecondName(userIn.getSecondName());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setEmail(userIn.getEmail());
        user.setPhoneNumber(userIn.getPhoneNumber());
        user.setUrlImage(userIn.getUrlImage());
        user =userRepository.save(user);

        user.getRoles().add(roleRepository.findByRole("User").get());

        try {
            LOG.info("Save user {} ", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception ex) {
            LOG.error("Error during registration", ex.getMessage());
            throw new UserExistException("The user " + userIn.getUsername() + " already exist.Please check your credentials");
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal) {

        User user = getUserByPrincipal(principal);
        user.setFirstName(userDTO.getFirstname());
        user.setSecondName(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setUrlImage(userDTO.getUrlImage());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        LOG.info("Update user {} ", userDTO.getEmail());
        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {

        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }

    public User getUserByID(Long userId) {
        return userRepository.findUserById(userId).get();
    }

}
