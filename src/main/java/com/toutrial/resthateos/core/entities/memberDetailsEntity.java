package com.toutrial.resthateos.core.entities;

import javax.persistence.*;

@Entity
@Table(name="t_member_details")
public class memberDetailsEntity {

    @Id
    @Column(name = "Id")
    private Long Id;

    private String country;
    private String provinance;
    private String streetName;
    private String streetNumber;
    private String buissnessDescription;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvinance() {
        return provinance;
    }

    public void setProvinance(String provinance) {
        this.provinance = provinance;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBuissnessDescription() {
        return buissnessDescription;
    }

    public void setBuissnessDescription(String buissnessDescription) {
        this.buissnessDescription = buissnessDescription;
    }



    @OneToOne
    @JoinColumn(name = "Id")
    private membersEntity member;
}
