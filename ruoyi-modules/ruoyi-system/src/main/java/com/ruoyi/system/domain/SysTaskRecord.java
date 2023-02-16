package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @filename: SysTaskRecord
 * @author: HXJ
 * @date: 2023/2/16 4:04 PM
 * @version: V1.0
 * @description: 任务记录对象 sys_task_record
 */
public class SysTaskRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID
     */
    private Long recordId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 记录的情况
     0：任务尚未开始
     1：任务被终止
     2：任务已完成
     3：任务进行中
     4：任务存在异常 */
    @Excel(name = "记录的情况
            0：任务尚未开始
            1：任务被终止
            2：任务已完成
            3：任务进行中
            4：任务存在异常")
            private Long status;

            /** 参与人员 */
            @Excel(name = "参与人员")
            private String member;

            public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setMember(String member)
    {
        this.member = member;
    }

    public String getMember()
    {
        return member;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("taskId", getTaskId())
                .append("status", getStatus())
                .append("member", getMember())
                .append("remark", getRemark())
                .toString();
    }
}
