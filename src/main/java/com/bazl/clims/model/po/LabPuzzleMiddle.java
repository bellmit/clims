package com.bazl.clims.model.po;

public class LabPuzzleMiddle {
    private String id;

    private String taskId;

    private String pcrId;

    private String syId;

    public String getSyId() {
        return syId;
    }

    public void setSyId(String syId) {
        this.syId = syId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getPcrId() {
        return pcrId;
    }

    public void setPcrId(String pcrId) {
        this.pcrId = pcrId == null ? null : pcrId.trim();
    }
}