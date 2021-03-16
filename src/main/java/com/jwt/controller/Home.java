package com.jwt.controller;

import com.jwt.model.JwtRequest;
import com.jwt.model.User;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class Home {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/welcome")
    public String welcome() {
        String text= "this is private page ";
        text += "this page is not allowed to unauthenticated users";
        return text;
    }

    @RequestMapping("/getusers")
    public String getusers()
    {
        return "{\"name\":\"Durgesh\"}";
    }



    @RequestMapping(value = "/registration", method =  RequestMethod.POST)
    public ResponseEntity<?> gT(@RequestBody JwtRequest jwtRequest) throws Exception
    {

        // storing data into data base
        System.out.println(jwtRequest);
        try{

            User user = new User();
            user.setId(jwtRequest.getId());
            user.setEmail(jwtRequest.getEmail());
            user.setPassword(jwtRequest.getPassword());
            user.setUsername(jwtRequest.getUsername());

            User save = userRepository.save(user);

            System.out.println(save);
            return ResponseEntity.ok("{\"Welcome to coffee shop\":\"Vimal\"}");
        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

    }

}
