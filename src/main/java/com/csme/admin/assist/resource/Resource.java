package com.csme.admin.assist.resource;

import com.csme.admin.assist.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "RESOURCE_TABLE")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Resource {

    @Id
    @GeneratedValue
    private long resourceId;
    @Size(min = 2 , message = "first name of the resource should be atleast 2 characters")
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(min = 2, message = "last name of the resource should be atleast 2 characters")
    @Column(name = "LAST_NAME")
    private String lastName;
    @Past(message = "birth date of the resource can only be in the past")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Past(message = "joining date of the resource can only be in the past")
    @Column(name = "JOINING_DATE")
    private Date joiningDate;
    @Column(name = "ACTIVE_STATUS")
    private boolean status;
    @Column(name = "APPROVER_ID")
    private long approverId;
    @Column(name = "APPROVER_STATUS")
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "RESOURCE_ROLES_TABLE",
            joinColumns = { @JoinColumn(name = "RESOURCE_id") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    private List<Role> roles;
    @Column(name = "EMAIL_ADDRESS")
    @Email
    private String emailAddress;


}
