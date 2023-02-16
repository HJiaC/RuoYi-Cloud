package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @filename: SysScheduleRecord
 * @author: HXJ
 * @date: 2023/2/16 3:57 PM
 * @version: V1.0
 * @description: 人员调度记录对象 sys_schedule_record
 */
public class SysScheduleRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调度ID */
    private Long scheduleId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 被调度人员编号 */
    @Excel(name = "被调度人员编号")
    private Long memberId;

    /** 被调度的原因 */
    @Excel(name = "被调度的原因")
    private String reason;

    /** 原位置 */
    @Excel(name = "原位置")
    private String from;

    /** 目标位置 */
    @Excel(name = "目标位置")
    private String to;

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("scheduleId", getScheduleId())
                .append("taskId", getTaskId())
                .append("memberId", getMemberId())
                .append("reason", getReason())
                .append("from", getFrom())
                .append("to", getTo())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
