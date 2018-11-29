package net.manolispapadimitriou.cryptonewsbackend.controllers;

import net.manolispapadimitriou.cryptonewsbackend.models.User;
import net.manolispapadimitriou.cryptonewsbackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> all(){
        List <User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    //user login
    @GetMapping( "/login/{login_credential}/{password}")
    public int login(@PathVariable("login_credential") String login_credential, @PathVariable("password") String password)  {
        var user = Optional.ofNullable(userRepository.findByUsername(login_credential));
        if(user.isEmpty()){
            var email = Optional.ofNullable(userRepository.findByEmail(login_credential));
            if(email.isEmpty()){

            }
        }

        return 0;
    }


    //user register

}
