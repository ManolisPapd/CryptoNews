package net.manolispapadimitriou.cryptonewsbackend.controller;

import net.manolispapadimitriou.cryptonewsbackend.model.CustomResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomAuthController {

    private CustomResponse customResponse;
    @GetMapping("/")
    public CustomResponse customAuth(){
        customResponse = new CustomResponse(200,"user authorized");
        return customResponse;
    }
}
