package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 补贴发放对象 sys_subsidy
 *
 * @author ruoyi
 * @date 2023-02-20
 */
public class SysSubsidy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 补贴记录ID */
    private Long subsidyId;

    /** 打卡记录ID */
    @Excel(name = "打卡记录ID")
    private Long recordId;

    /** 打卡记录所属任务ID */
    @Excel(name = "打卡记录所属任务ID")
    private Long taskId;

    /** 补贴金额 */
    @Excel(name = "补贴金额")
    private Long money;

    public void setSubsidyId(Long subsidyId)
    {
        this.subsidyId = subsidyId;
    }

    public Long getSubsidyId()
    {
        return subsidyId;
    }
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
    public void setMoney(Long money)
    {
        this.money = money;
    }

    public Long getMoney()
    {
        return money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("subsidyId", getSubsidyId())
                .append("recordId", getRecordId())
                .append("taskId", getTaskId())
                .append("money", getMoney())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .toString();
    }
}
