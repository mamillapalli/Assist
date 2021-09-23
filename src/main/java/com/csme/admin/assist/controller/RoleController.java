package com.csme.admin.assist.controller;


import com.csme.admin.assist.entity.Role;
import com.csme.admin.assist.model.RoleDTO;
import com.csme.admin.assist.repository.RoleRepository;
import com.csme.admin.assist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    //TO GET A LIST OF ROLES FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @GetMapping()
    public List<RoleDTO> getAllRoles()
    {
        return roleService.getAll();
    }

/*
    //TO GET A ROLE DETAILS FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @GetMapping(path="/roles/{id}")
    public Optional<Role> getAllRoles(@PathVariable @Valid int id) throws Exception {
        Optional<Role> role = roleService.findById(id);
        if(role.isPresent()) return role;
        throw new Exception("Role with id-->" + id + " not found");
    }
*/

    //TO ADD A ROLE FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @PostMapping(path = "/roles")
    public Role saveRole(@Valid @RequestBody Role role)
    {
        //return roleService.save(role);
        return null;

    }


    // TO MODIFY A ROLE FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @PutMapping(path = "/roles/{id}")
    public ResponseEntity<Role> saveRole(@PathVariable(value = "id") Integer id, @Valid @RequestBody Role roleDetails) throws Exception {
/*        Role role = roleService.findById(id).orElseThrow(() -> new Exception("role wit id -> " + id + " not found"));
        role.setName(roleDetails.getName());
        final Role updatedRole = roleService.save(role);
        return ResponseEntity.ok(updatedRole);*/
        return null;
    }

}
