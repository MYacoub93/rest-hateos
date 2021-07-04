package com.toutrial.resthateos.core.repository;

import com.toutrial.resthateos.core.entities.membersEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface membersRepository extends CrudRepository<membersEntity,Long> {

    Set<membersEntity> findAllByFirstNameOrLastName(String firstName,String lastName);

    membersEntity findById(long Id);

}
