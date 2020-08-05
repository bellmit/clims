package com.bazl.clims.model.po;

import java.math.BigDecimal;

public class MarkerInfo {
    private String id;

    private String panelId;

    private String markerName;

    private String markerAlias;

    private BigDecimal markerOrder;

    private String markerDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId == null ? null : panelId.trim();
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName == null ? null : markerName.trim();
    }

    public String getMarkerAlias() {
        return markerAlias;
    }

    public void setMarkerAlias(String markerAlias) {
        this.markerAlias = markerAlias == null ? null : markerAlias.trim();
    }

    public BigDecimal getMarkerOrder() {
        return markerOrder;
    }

    public void setMarkerOrder(BigDecimal markerOrder) {
        this.markerOrder = markerOrder;
    }

    public String getMarkerDesc() {
        return markerDesc;
    }

    public void setMarkerDesc(String markerDesc) {
        this.markerDesc = markerDesc == null ? null : markerDesc.trim();
    }
}