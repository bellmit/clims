package com.bazl.clims.model.po;

import java.io.Serializable;

/**
 * Created by Liuchang on 2020/7/6.
 * 类说明： 指控编码设置
 */
public class QcNoSettings  implements Serializable{

    private static final long serialVersionUID = 1L;

    private String Id;//主键编号

    private String labServerNo; //实验室编号

    private String labServerHeader;//实验室标头

    private String dictCode; //对应字典项类型

    private String barcode; //编码


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLabServerNo() {
        return labServerNo;
    }

    public void setLabServerNo(String labServerNo) {
        this.labServerNo = labServerNo;
    }

    public String getLabServerHeader() {
        return labServerHeader;
    }

    public void setLabServerHeader(String labServerHeader) {
        this.labServerHeader = labServerHeader;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
