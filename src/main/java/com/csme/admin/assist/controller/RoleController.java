package com.csme.admin.assist.controller;


import com.csme.admin.assist.model.ModifyRoleDTO;
import com.csme.admin.assist.model.RoleDTO;
import com.csme.admin.assist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    //TO GET A LIST OF ROLES FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @GetMapping()
    public ResponseEntity<List<RoleDTO>> getAllRoles()
    {
        return new ResponseEntity<>(roleService.getAll(),HttpStatus.OK);
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
    @PostMapping()
    public ResponseEntity<RoleDTO> addRole(@Valid @RequestBody RoleDTO roleDTO)
    {
        return new ResponseEntity<>(roleService.add(roleDTO),HttpStatus.ACCEPTED);


    }


    // TO MODIFY A ROLE FOR ADMIN USERS
    //@Secured({"ADMIN","MANAGER"})
    @PutMapping()
    public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody ModifyRoleDTO roleDetails)  {
        return new ResponseEntity<>(roleService.update(roleDetails), HttpStatus.ACCEPTED);
    }

}
