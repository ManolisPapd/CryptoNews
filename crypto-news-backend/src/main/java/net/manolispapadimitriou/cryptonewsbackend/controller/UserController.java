package net.manolispapadimitriou.cryptonewsbackend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.manolispapadimitriou.cryptonewsbackend.exception.CustomException;
import net.manolispapadimitriou.cryptonewsbackend.model.User;
import net.manolispapadimitriou.cryptonewsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {


    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @GetMapping("/all")
    public List<User> all(){
        List <User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }



    //user register
    @PostMapping("/sign-up")
    public ObjectNode signUp(@RequestBody User user){
        ObjectNode objectNode = mapper.createObjectNode();

        //TODO bug otan kanei login me username kai email pou uparxoun alla anhkoun se diaforetika atoma
        //Des NoteController.java exei balei var!, dokimase na baleis var sto return tou repo
        User userFromDB = userRepository.findByUsernameOrEmail(user.getUsername(),user.getEmail());
        Optional.ofNullable(userFromDB).ifPresentOrElse(e-> {throw new CustomException("User already exists");}, //If the user exists exception, else new user will be added to DB
                ()-> {saveNewUserToDb(user);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    objectNode.put("timestamp",timestamp.toString());
                    objectNode.put("status","200");
                    objectNode.put("message","user added to database");
                    });




        return objectNode;

    }


    public void saveNewUserToDb(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
