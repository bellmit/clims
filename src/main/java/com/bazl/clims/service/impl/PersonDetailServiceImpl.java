package com.bazl.clims.service.impl;


import com.bazl.clims.dao.PersonDetailMapper;
import com.bazl.clims.service.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by hj on 2018/12/20.
 */
@Service
public class PersonDetailServiceImpl implements PersonDetailService {

    @Autowired
    PersonDetailMapper personDetailMapper;


}
