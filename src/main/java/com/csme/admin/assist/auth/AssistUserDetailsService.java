package com.csme.admin.assist.auth;

import com.csme.admin.assist.resource.Resource;
import com.csme.admin.assist.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssistUserDetailsService implements UserDetailsService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        Resource resource= resourceRepository.findByEmailAddress(emailAddress);
        if(resource == null) throw new RuntimeException("user with the given email address not found");
        return new AssistUserDetails(resource);
    }


}
