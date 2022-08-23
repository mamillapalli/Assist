package com.csme.admin.assist.controller;


import com.csme.admin.assist.jwtauthentication.configuration.service.JWTUtil;
import com.csme.admin.assist.model.ResourceDTO;

import com.csme.admin.assist.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController

@Slf4j
@RequestMapping("/api/v1/resources")
public class ResourceController {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ResourceService resourceService;


    // TO GET ALL RESOURCES FOR ADMIN USERS
   // @Secured({"ADMIN","MANAGER"})
    //@PreAuthorize("hasRole('LEAVE_ADMIN')")
    @GetMapping()
    public List<ResourceDTO> getAllResources()
    {
        return resourceService.getAll();
    }

/*
    //Debugging end point
    @GetMapping("/printRoles")
    public String getUsers() {
        StringBuilder authorities = new StringBuilder();
        log.info("Authorites on the user-->"  );
        UserDetails details = userDetailsService.loadUserByUsername("ravi@chinasystems-me.com");
        if (details != null) {
            log.info("Name on the user-->" +   details.getUsername());
            log.info("Number on the authorities for the user-->" +   details.getAuthorities().size());
            details.getAuthorities().forEach((grantedAuthority) -> log.info("Authorites on the user-->" + grantedAuthority));
        }
        return "";
    }
*/

    //TO GET RESOURCE DETAILS FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @GetMapping(path="/{emailAddress}")
    public ResourceDTO getResourceByEmailAddress(@PathVariable(name ="emailAddress") String emailAddress)
    {
        return resourceService.getResourceByEmailAddress(emailAddress);

    }

    //TO ADD A RESOURCE FOR ADMIN USERS
    @PostMapping()
   // @Secured({"ADMIN","MANAGER"})
    public ResourceDTO saveResource(@Valid  @RequestBody ResourceDTO resourceDTO)
    {
        return  resourceService.add(resourceDTO);

    }

    //TO MODIFY A RESOURCE FOR ADMIN USERS
   // @Secured({"ADMIN","MANAGER"})
    @PutMapping()
    public ResponseEntity<ResourceDTO> updateResource(@Valid @RequestBody ResourceDTO resourceDetails)
    {

        return new ResponseEntity<>(resourceService.update(resourceDetails), HttpStatus.ACCEPTED);

    }


}
