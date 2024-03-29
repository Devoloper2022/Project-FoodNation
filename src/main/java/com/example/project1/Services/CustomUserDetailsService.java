package com.example.project1.Services;

import com.example.project1.Domain.User;
import com.example.project1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("This is" + email + " email not found"));
        return build(user);
    }


    public User loadUserById(Long id){
        return userRepository.findUserById(id)
                .orElse(null);
    }

    public static User build(User user){
//        List<GrantedAuthority> authorities= (List<GrantedAuthority>) user.getRole();
        List<GrantedAuthority> authorities= user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                authorities);
    }
}
