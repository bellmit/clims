package com.bazl.clims.model;


public class TreeData {

    private String id;
    private String pId = "0";
    private String name;
    private Boolean open = true;
    private Boolean isParent;
    private Boolean edit = true;
    private Boolean showAdd = true;
    private Boolean nocheck = false;
    private Boolean checked = false;
    private String tree_url;
    private String  activeflag;
    private String  rootflag;
    private String  deleteflag;
    private String  subsystemFlag;
    private String  orderNo;

    public String getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getRootflag() {
        return rootflag;
    }

    public void setRootflag(String rootflag) {
        this.rootflag = rootflag;
    }

    public String getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(String activeflag) {
        this.activeflag = activeflag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getShowAdd() {
        return showAdd;
    }

    public void setShowAdd(Boolean showAdd) {
        this.showAdd = showAdd;
    }

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getTree_url() {
        return tree_url;
    }

    public void setTree_url(String tree_url) {
        this.tree_url = tree_url;
    }

    public TreeData() {
    }

    public TreeData(String id, String pId, String name, Boolean edit) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.edit = edit;
    }

    public String getSubsystemFlag() {
        return subsystemFlag;
    }

    public void setSubsystemFlag(String subsystemFlag) {
        this.subsystemFlag = subsystemFlag;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
