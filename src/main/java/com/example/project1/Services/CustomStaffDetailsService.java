package com.example.project1.Services;

import com.example.project1.Domain.Staff;
import com.example.project1.Repository.StaffRepository;
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
public class CustomStaffDetailsService implements UserDetailsService {

    private final StaffRepository staffRepository;

    @Autowired
    public CustomStaffDetailsService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findStaffByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("This is" + username + " username not foud"));
        return build(staff);
    }

    public Staff loadStaffById(Long id){
        return staffRepository.findStaffById(id)
                .orElse(null);
    }

    public static Staff build(Staff staff){
        List<GrantedAuthority> authorities= staff.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new Staff(
                staff.getId(),
                staff.getUsername(),
                staff.getPassword(),
                staff.getEmail(),
                authorities);
    }
}
