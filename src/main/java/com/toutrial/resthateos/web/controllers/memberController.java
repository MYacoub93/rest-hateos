package com.toutrial.resthateos.web.controllers;


import com.toutrial.resthateos.core.entities.memberDetailsEntity;
import com.toutrial.resthateos.core.entities.membersEntity;
import com.toutrial.resthateos.core.services.membersBl;
import com.toutrial.resthateos.web.models.actionModel;
import com.toutrial.resthateos.web.models.memberDetailsModel;
import com.toutrial.resthateos.web.models.memberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/member/")
public class memberController {

    @Autowired
    membersBl membersService;

    @GetMapping(path = "getByName/{name}")
    public CollectionModel<memberModel> getMemberByName(@PathVariable("name") String name){
        List<memberModel> members = new ArrayList<>();

        membersService.getAllByName(name).stream().forEach(membersEntity -> {
            memberModel member = new memberModel();
            member.setId(membersEntity.getId());
            member.setCreationDate(membersEntity.getCreationDate());
            member.setFirstName(membersEntity.getFirstName());
            member.setLastName(membersEntity.getLastName());
            member.setEmail(membersEntity.getEmail());
            member.setTelephoneNumber(membersEntity.getTelephoneNumber());

            //Link selfLink = linkTo(methodOn(memberController.class)).slash(name).withSelfRel();
            //member.add(selfLink);

            Link memberLink = linkTo(methodOn(memberController.class)
                    .getById(member.getId())).withRel("getById");
            member.add(memberLink);

            Link memberDetailsLink = linkTo(methodOn(memberController.class)
                    .getDetailsById(member.getId())).withRel("getDetailsById");
            member.add(memberDetailsLink);

            members.add(member);
        });

        Link link = linkTo(memberController.class).slash("getByName").slash(name).withSelfRel();
        CollectionModel<memberModel> result = CollectionModel.of(members, link);
        return result;
    }

    @GetMapping(path = "getById/{Id}")
    public memberModel getById(@PathVariable("Id") long Id){
        membersEntity membersEntity  = membersService.getById(Id);
        memberModel member = new memberModel();
        member.setId(membersEntity.getId());
        member.setFirstName(membersEntity.getFirstName());
        member.setLastName(membersEntity.getLastName());
        member.setEmail(membersEntity.getEmail());
        member.setTelephoneNumber(membersEntity.getTelephoneNumber());
        member.setCreationDate(membersEntity.getCreationDate());

        Link link = linkTo(memberController.class).slash("getById").slash(Id).withSelfRel();
        member.add(link);

        return member;
    }


    @PostMapping(path = "createMember")
    public actionModel createMember(@RequestBody memberModel member){

        membersEntity memberRecord = new membersEntity();
        memberRecord.setFirstName( member.getFirstName());
        memberRecord.setLastName(member.getLastName());
        memberRecord.setEmail(member.getEmail());
        memberRecord.setTelephoneNumber(member.getTelephoneNumber());
        memberRecord =  membersService.createMember(memberRecord);

        actionModel result = new actionModel();
        if(memberRecord.getId() > 0){
            result.setAction(true);
            result.setMessage("success");

            Link memberLink = linkTo(methodOn(memberController.class)
                    .getById(memberRecord.getId())).withRel("getById");
            result.add(memberLink);
        }else{
            result.setAction(false);
            result.setMessage("failed");
        }
        return result;
    }

    @PostMapping(path = "getDetailsById/{Id}")
    public memberDetailsModel getDetailsById(long Id){
        Optional<memberDetailsEntity> mDetails = membersService.getDetailsById(Id);

        memberDetailsModel model = new memberDetailsModel();
        mDetails.ifPresent(memberDetailsEntity -> {
            model.setId(memberDetailsEntity.getId());
            model.setCountry(memberDetailsEntity.getCountry());
            model.setProvinance(memberDetailsEntity.getProvinance());
            model.setStreetName(memberDetailsEntity.getStreetName());
            model.setStreetNumber(memberDetailsEntity.getStreetNumber());
            model.setBuissnessDescription(memberDetailsEntity.getBuissnessDescription());

            Link link = linkTo(memberController.class).slash("getDetailsById").slash(Id).withSelfRel();
            model.add(link);
        });

        return model;

    }

}
