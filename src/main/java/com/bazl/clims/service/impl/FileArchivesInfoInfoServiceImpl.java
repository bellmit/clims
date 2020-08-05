package com.bazl.clims.service.impl;


import com.bazl.clims.dao.FileArchivesInfoMapper;
import com.bazl.clims.model.po.FileArchivesInfo;
import com.bazl.clims.service.FileArchivesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/12/21.
 */
@Service
public class FileArchivesInfoInfoServiceImpl implements FileArchivesInfoService {

    @Autowired
    private FileArchivesInfoMapper fileArchivesInfoMapper;

    @Override
    public int insert(FileArchivesInfo fileArchivesInfo) {
        return fileArchivesInfoMapper.insert(fileArchivesInfo);
    }

    @Override
    public FileArchivesInfo queryFileLetter(String caseId) {
        return fileArchivesInfoMapper.queryFileLetter(caseId);
    }

    @Override
    public FileArchivesInfo queryEntrustBook(String caseId) {
        return fileArchivesInfoMapper.queryEntrustBook(caseId);
    }

}
