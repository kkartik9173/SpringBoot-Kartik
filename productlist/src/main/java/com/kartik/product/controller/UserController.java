package com.kartik.product.controller;

import com.kartik.product.dto.UserDTO;
import com.kartik.product.entity.User;
import com.kartik.product.security.JwtUtil;
import com.kartik.product.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register")
    public User register(@RequestBody User user){
        System.out.println("in usercontroller class "+ user);

        return myUserDetailsService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());
        String generatedToken = jwtUtil.generateToken(userDetails.getUsername(), roles);
        System.out.println("UserController.login- generateToken "+ generatedToken);

        return generatedToken;
    }

}
