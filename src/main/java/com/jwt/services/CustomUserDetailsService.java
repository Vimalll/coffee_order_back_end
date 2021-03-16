package com.jwt.services;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.User;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

     @Override
     // loadUserByUsername takes user login details from frontend
     public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

         User user = this.userRepository.findByUsername(userName);

         if(user == null)
        {
            throw new UsernameNotFoundException("User not found !!");
        } else
        {
            return new CustomUserDetails(user);
        }

//        if(userName.equals("Durgesh"))
//        {
//            return new User("Durgesh", "Durgesh123", new ArrayList<>() );
//        }
//        else {
//            throw new UsernameNotFoundException("User not found !!");
//        }

    }
}

