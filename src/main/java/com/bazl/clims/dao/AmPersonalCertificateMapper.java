package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmPersonalCertificate;
import java.util.List;

public interface AmPersonalCertificateMapper {
    int insert(AmPersonalCertificate record);

    List<AmPersonalCertificate> selectAll();
}