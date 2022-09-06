package softuni.workshop.service.services.impl;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.User;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;


@Service
public class UserServiceImpl implements UserService {


    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AppUserDetailsService appUserDetailsService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AppUserDetailsService appUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {

        User user = new User()
                .setEmail(userRegisterModel.getEmail())
                .setPassword(this.passwordEncoder.encode(userRegisterModel.getPassword()))
                .setUsername(userRegisterModel.getUsername());

        this.userRepository.save(user);



        UserDetails userDetails = this.appUserDetailsService.loadUserByUsername(userRegisterModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.
                getContext()
                .setAuthentication(authentication);

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void initUsers(){

        if(this.userRepository.count() == 0){

            User user = new User()
                    .setUsername("test")
                    .setPassword(passwordEncoder.encode("123456"))
                    .setEmail("test@example.com");
            this.userRepository.save(user);
        }


    }
}
