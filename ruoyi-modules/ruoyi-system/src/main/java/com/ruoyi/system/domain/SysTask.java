package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @filename: SysTask
 * @author: HXJ
 * @date: 2023/2/16 3:23 PM
 * @version: V1.0
 * @description: Web端任务中心任务对象 sys_task
 */
public class SysTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID（唯一区分任务的标志） */
    private Long taskId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    @Excel(name = "创建人员")
    private String createBy;

    /** 所属组织 */
    @Excel(name = "所属组织")
    private String organization;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long status;

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getOrganization()
    {
        return organization;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "SysTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", organization='" + organization + '\'' +
                ", status=" + status +
                '}';
    }
}
