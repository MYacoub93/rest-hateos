package com.toutrial.resthateos.core.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="t_members")
public class membersEntity {

    @javax.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

    @Column(name = "creationDate")
    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id", referencedColumnName = "Id")
    private memberDetailsEntity memberDetails;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public memberDetailsEntity getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(memberDetailsEntity memberDetails) {
        this.memberDetails = memberDetails;
    }
}
