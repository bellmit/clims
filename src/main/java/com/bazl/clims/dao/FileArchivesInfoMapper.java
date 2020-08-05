package com.bazl.clims.dao;

import com.bazl.clims.model.po.FileArchivesInfo;

import java.util.List;

public interface FileArchivesInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileArchivesInfo record);

    FileArchivesInfo selectByPrimaryKey(String id);

    List<FileArchivesInfo> selectAll();

    int updateByPrimaryKey(FileArchivesInfo record);

    FileArchivesInfo queryFileLetter(String caseId);

    FileArchivesInfo queryEntrustBook(String caseId);
}