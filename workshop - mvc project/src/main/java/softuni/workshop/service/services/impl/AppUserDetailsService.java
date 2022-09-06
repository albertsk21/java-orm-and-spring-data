package softuni.workshop.service.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Role;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.error.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        var user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND));

        return new User(
                user.getUsername(),
                user.getPassword(),
                this.asGrantedAuthority(user.getRoles())
        );
    }


    public List<GrantedAuthority> asGrantedAuthority(List<Role> roles){
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getAuthority()))
                .collect(Collectors.toList());
    }
}

