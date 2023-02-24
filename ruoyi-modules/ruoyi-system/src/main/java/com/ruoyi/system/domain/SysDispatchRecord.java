package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @filename: SysDispatchRecord
 * @author: HXJ
 * @date: 2023/2/23 1:59 PM
 * @version: V1.0
 * @description:
 */
public class SysDispatchRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调度ID */
    private Long dispatchId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 被调度人员编号 */
    @Excel(name = "被调度人员")
    private String member;

    /** 被调度的原因 */
    @Excel(name = "被调度的原因")
    private String reason;

    /** 原位置 */
    @Excel(name = "原位置")
    private String from;

    /** 目标位置 */
    @Excel(name = "目标位置")
    private String to;

    public void setDispatchId(Long dispatchId)
    {
        this.dispatchId = dispatchId;
    }

    public Long getDispatchId()
    {
        return dispatchId;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getFrom()
    {
        return from;
    }
    public void setTo(String to)
    {
        this.to = to;
    }

    public String getTo()
    {
        return to;
    }
}
