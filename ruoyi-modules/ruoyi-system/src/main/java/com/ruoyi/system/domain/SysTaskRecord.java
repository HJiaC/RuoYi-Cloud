package com.ruoyi.system.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;

public class SysTaskRecord extends BaseEntity {
    private int recordId;
    private int taskId;
    private int status;

    private String taskName;
    private String member;

    private String remark;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysTaskRecord{" +
                "recordId=" + recordId +
                ", taskId=" + taskId +
                ", status=" + status +
                ", member='" + member + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}