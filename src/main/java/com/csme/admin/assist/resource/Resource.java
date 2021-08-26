package com.csme.admin.assist.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "RESOURCE_TABLE")
public class Resource {

    @Id
    @GeneratedValue
    private long resourceId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "JOINING_DATE")
    private Date joiningDate;
    @Column(name = "ACTIVE_STATUS")
    private boolean status;
    @Column(name = "APPROVER_ID")
    private long approverId;
    @Column(name = "APPROVER_STATUS")
    private boolean approver;
    @Column(name = "ADMIN_STATUS")
    private boolean admin;

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", joiningDate=" + joiningDate +
                ", status=" + status +
                ", approverId=" + approverId +
                ", approver=" + approver +
                ", admin=" + admin +
                '}';
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getApproverId() {
        return approverId;
    }

    public void setApproverId(long approverId) {
        this.approverId = approverId;
    }

    public boolean isApprover() {
        return approver;
    }

    public void setApprover(boolean approver) {
        this.approver = approver;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Resource(long resourceId, String firstName, String lastName, Date birthDate, Date joiningDate, boolean status, long approverId, boolean approver, boolean admin) {
        this.resourceId = resourceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.joiningDate = joiningDate;
        this.status = status;
        this.approverId = approverId;
        this.approver = approver;
        this.admin = admin;
    }

    public Resource() {
    }
}
