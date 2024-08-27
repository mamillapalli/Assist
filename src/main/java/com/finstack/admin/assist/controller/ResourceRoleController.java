package com.finstack.admin.assist.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.finstack.admin.assist.entity.Resource;
import com.finstack.admin.assist.model.ResourceRolesDTO;
import com.finstack.admin.assist.repository.ResourceRepository;
import com.finstack.admin.assist.repository.RoleRepository;
import com.finstack.admin.assist.service.ResourceRolesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Valid
@Slf4j
@RequestMapping("/resourceroles")
public class ResourceRoleController {



    @Autowired
    ResourceRolesService resourceRolesService;

    // TO GET A LIST OF RESOURCES AND THEIR ROLES FOR ADMIN USERS
    @GetMapping()
    public ResponseEntity<List<ResourceRolesDTO>> listRolesOfAllResources()
    {
        return new ResponseEntity<>(resourceRolesService.getRolesOfAllResources(), HttpStatus.OK);
    }

    @PutMapping("/assign")
    public ResponseEntity<ResourceRolesDTO> assignRolesToResource(@RequestBody ResourceRolesDTO resourceRolesDTO)
    {
        return new ResponseEntity<>(resourceRolesService.assignRolesToResource(resourceRolesDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping("/modify")
    public ResponseEntity<ResourceRolesDTO> modifyResourceRoles(@RequestBody ResourceRolesDTO resourceRolesDTO)
    {
        return new ResponseEntity<>(resourceRolesService.modifyResourceRoles(resourceRolesDTO), HttpStatus.ACCEPTED);
    }

}
