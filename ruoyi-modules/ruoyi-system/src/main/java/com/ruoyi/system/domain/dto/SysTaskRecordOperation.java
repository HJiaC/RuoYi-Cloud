package com.ruoyi.system.domain.dto;

/**
 * @filename: SysTaskRecordOperation
 * @author: HXJ
 * @date: 2023/2/18 2:15 PM
 * @version: V1.0
 * @description: 接收前端修改任务记录状态的数据（记录ID，操作，操作备注）
 */
public class SysTaskRecordOperation {

    private int recordId;

    private int operation;

    private String remark;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysTaskRecordOperation{" +
                "recordId=" + recordId +
                ", operation=" + operation +
                ", remark='" + remark + '\'' +
                '}';
    }
}
