package com.bazl.clims.dao;


import com.bazl.clims.model.po.LimsPerosnRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface LimsPerosnRelationMapper {

    void insertPersonRelation(LimsPerosnRelation limsPerosnRelation);

    void deleteBySourcePersonId(LimsPerosnRelation limsPerosnRelation);

    LimsPerosnRelation selectBySourcePersonId(String sourcePersonId);

    void updatePerosnRelation(LimsPerosnRelation limsPerosnRelation);
}