package com.csme.admin.assist.jwtauthentication.controller;

import com.csme.admin.assist.jwtauthentication.configuration.model.AuthenticationRequestDetails;
import com.csme.admin.assist.jwtauthentication.configuration.model.AuthenticationResponseDetails;
import com.csme.admin.assist.jwtauthentication.configuration.service.JWTUtil;
import com.csme.admin.assist.jwtauthentication.configuration.service.RSAUtil;
import com.csme.admin.assist.jwtauthentication.configuration.service.SecurityUserDetailsService;
import com.csme.admin.assist.model.ResourceDTO;
import com.csme.admin.assist.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Profile("JWT")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    RSAUtil rsaUtil;

    @Autowired
    UserDetailsService securityUserDetailsService;

    @Autowired
    ResourceService resourceService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponseDetails> authenticate(@RequestBody AuthenticationRequestDetails authenticationRequestDetails) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDetails.getEmailAddress(), authenticationRequestDetails.getPassword())
            );
        }
        catch (BadCredentialsException be) {
            throw new Exception("Incorrect username or password", be);
        }
        catch(Exception e) {
            if(e.getMessage().contains("AuthenticationException"))  throw new Exception("Incorrect username or password", e);
            System.out.println("exception message is " + e.getMessage());
        }

        final UserDetails userDetails = securityUserDetailsService
                .loadUserByUsername(authenticationRequestDetails.getEmailAddress());

        final String jwt = jwtUtil.generateToken(userDetails,rsaUtil.getPrivateKey());

        return new ResponseEntity<>(new AuthenticationResponseDetails(jwt),HttpStatus.OK);
    }


    @GetMapping(path = "/hello")
    public String sayHello()
    {
        return "Hello World";
    }

    @GetMapping (path = "/profile")
    public Optional<ResourceDTO> getProfile()
    {
        Optional<ResourceDTO> resourceDTO = Optional.ofNullable(resourceService.getResourceByEmailAddress(jwtUtil.extractUsernameFromRequest()));
        return resourceDTO;
    }

}
