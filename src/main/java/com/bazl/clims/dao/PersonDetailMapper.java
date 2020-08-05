package com.bazl.clims.dao;


import com.bazl.clims.model.po.PersonDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailMapper {

    void updatePersonDetail1(PersonDetail personDetail);

    void deleteByDetailId(String personDetailId);

    PersonDetail selectByDetailId(String personDetailId);
}