package com.csme.admin.assist.resource;


import com.csme.admin.assist.resourcerole.ResourceRoleRepository;
import com.csme.admin.assist.role.RoleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@Valid
@Slf4j
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceRoleRepository resourceRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsService userDetailsService;


    // TO GET ALL RESOURCES FOR ADMIN USERS
    @Secured({"ADMIN","MANAGER"})
    //@PreAuthorize("hasRole('LEAVE_ADMIN')")
    @GetMapping(path="/resources", name="getAllResources")
    public List<Resource> getAllResources()
    {
        return resourceRepository.findAll();
    }

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

    //TO GET RESOURCE DETAILS FOR ADMIN USERS
    @Secured({"ADMIN","MANAGER"})
    @GetMapping(path="/resources/{id}" ,name="getResourceById")
    public Optional<Resource> getResourceById(@PathVariable long id)
    {
        Optional<Resource> resource = resourceRepository.findById(id);
        if(resource.isPresent()) return resource;
        else throw new ResourceNotFoundException("Resource wit id -> " + id + " not found");
    }

    //TO ADD A RESOURCE FOR ADMIN USERS
    @PostMapping(path="/resources", name="saveResource")
    @Secured({"ADMIN","MANAGER"})
    public Resource saveResource(@Valid  @RequestBody Resource resource)
    {
        return  resourceRepository.save(resource);

    }

    //TO MODIFY A RESOURCE FOR ADMIN USERS
    @Secured({"ADMIN","MANAGER"})
    @PutMapping(path="/resources/{id}", name="updateResource")
    public ResponseEntity<Resource> updateResource(@PathVariable(value = "id") Long id, @Valid @RequestBody Resource resourceDetails)
    {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource wit id -> " + id + " not found"));

        resource.setBirthDate(resourceDetails.getBirthDate());
        resource.setApproverId(resourceDetails.getApproverId());
        resource.setFirstName(resourceDetails.getFirstName());
        resource.setLastName(resourceDetails.getLastName());
        resource.setJoiningDate(resourceDetails.getJoiningDate());
        resource.setStatus(resourceDetails.isStatus());
        final Resource updatedResource = resourceRepository.save(resource);
        return ResponseEntity.ok(updatedResource);
    }

}
