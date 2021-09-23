package com.csme.admin.assist.resourcerole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "RESOURCE_ROLES_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceRole {

    @Id
    @Column (name="SYSTEM_ID")
    private UUID uuid;
    @Column(name="RESROUCE_ID")
    private UUID resourceId;
    @Column(name="ROLE_ID")
    private UUID roleId;
}
