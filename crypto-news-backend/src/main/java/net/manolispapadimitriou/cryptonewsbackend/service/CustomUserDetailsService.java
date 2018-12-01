package net.manolispapadimitriou.cryptonewsbackend.service;

import net.manolispapadimitriou.cryptonewsbackend.model.User;
import net.manolispapadimitriou.cryptonewsbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),emptyList());
    }
}
