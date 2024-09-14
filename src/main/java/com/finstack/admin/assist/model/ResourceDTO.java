package com.finstack.admin.assist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finstack.admin.assist.entity.FrequencyEnum;
import com.finstack.admin.assist.entity.Role;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResourceDTO {

   // @Size(min = 2 , message = "first name of the resource should be atleast 2 characters")
   @Size(min = 2 )
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Dubai")
    private Date birthDate;
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Dubai")
    private Date joiningDate;
    private boolean status;
    @Email
    @NotEmpty
    private String emailAddress;
    private List<RoleDTO> roles;
    private String reportingTo;
    
    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
   // @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String contactNumber;
    @NotBlank(message = "Address should not be blank")
    private String contactAddress;
    
    @JsonProperty("leaveFrequency")
    private FrequencyEnum leaveFrequency;
    
    @JsonProperty("workFrequency")
    private FrequencyEnum workFrequency;

}
