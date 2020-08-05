package com.bazl.clims.model.po;

public class XckyAddressInfo {
    private String id;

    private String orgId;

    private String xckyAddress;

    private String xckySysName;

    private String defaultWhenNull;

    private String invokerIpaddr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getXckyAddress() {
        return xckyAddress;
    }

    public void setXckyAddress(String xckyAddress) {
        this.xckyAddress = xckyAddress == null ? null : xckyAddress.trim();
    }

    public String getXckySysName() {
        return xckySysName;
    }

    public void setXckySysName(String xckySysName) {
        this.xckySysName = xckySysName == null ? null : xckySysName.trim();
    }

    public String getDefaultWhenNull() {
        return defaultWhenNull;
    }

    public void setDefaultWhenNull(String defaultWhenNull) {
        this.defaultWhenNull = defaultWhenNull == null ? null : defaultWhenNull.trim();
    }

    public String getInvokerIpaddr() {
        return invokerIpaddr;
    }

    public void setInvokerIpaddr(String invokerIpaddr) {
        this.invokerIpaddr = invokerIpaddr == null ? null : invokerIpaddr.trim();
    }
}