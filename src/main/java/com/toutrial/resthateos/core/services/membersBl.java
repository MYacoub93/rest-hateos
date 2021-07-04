package com.toutrial.resthateos.core.services;

import com.toutrial.resthateos.core.entities.memberDetailsEntity;
import com.toutrial.resthateos.core.entities.membersEntity;
import com.toutrial.resthateos.core.repository.memberDetailsRepository;
import com.toutrial.resthateos.core.repository.membersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class membersBl {

    @Autowired
    private membersRepository members;

    @Autowired
    private memberDetailsRepository memberDetails;

    public membersEntity createMember(membersEntity member){
        try{
            member.setCreationDate(new Date(new java.util.Date().getTime()));
         return members.save(member);
        }catch (Exception ex) {
            return null;
        }
    }

    public Set<membersEntity> getAllByName(String name){
       return members.findAllByFirstNameOrLastName(name,name);
    }

    public membersEntity getById(long Id){
        return members.findById(Id);
    }

    public Optional<memberDetailsEntity> getDetailsById(long Id){
        return memberDetails.findById(Id);
    }

}
