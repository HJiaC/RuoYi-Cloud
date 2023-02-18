package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTaskMapper;
import com.ruoyi.system.domain.SysTask;
import com.ruoyi.system.service.ISysTaskService;

/**
 * @filename: SysTaskServiceImpl
 * @author: HXJ
 * @date: 2023/2/16 3:25 PM
 * @version: V1.0
 * @description: Web端任务中心任务Service业务层处理
 */
@Service
public class SysTaskServiceImpl implements ISysTaskService
{
    @Autowired
    private SysTaskMapper sysTaskMapper;


    /**
     * 改变任务状态
     * @param taskId
     * @return int
     */
    @Override
    public int changeTaskStatus(Long[] taskIds,Integer status) {
        return sysTaskMapper.changeTaskStatus(taskIds,status);
    }
    /**
     * 批量改变任务状态
     * @param taskIds
     * @return int
     */
    @Override
    public int changeTaskStatusByIds(Long[] taskIds, Integer status) {
        return sysTaskMapper.changeTaskStatusByIds(taskIds,status);
    }

    /**
     * 查询Web端任务中心任务
     *
     * @param taskId Web端任务中心任务主键
     * @return Web端任务中心任务
     */
    @Override
    public SysTask selectSysTaskByTaskId(Long taskId)
    {
        return sysTaskMapper.selectSysTaskByTaskId(taskId);
    }

    /**
     * 查询Web端任务中心任务列表
     *
     * @param sysTask Web端任务中心任务
     * @return Web端任务中心任务
     */
    @Override
    public List<SysTask> selectSysTaskList(SysTask sysTask)
    {
        return sysTaskMapper.selectSysTaskList(sysTask);
    }

    /**
     * 新增Web端任务中心任务
     *
     * @param sysTask Web端任务中心任务
     * @return 结果
     */
    @Override
    public int insertSysTask(SysTask sysTask)
    {
        sysTask.setCreateTime(DateUtils.getNowDate());
        return sysTaskMapper.insertSysTask(sysTask);
    }

    /**
     * 修改Web端任务中心任务
     *
     * @param sysTask Web端任务中心任务
     * @return 结果
     */
    @Override
    public int updateSysTask(SysTask sysTask)
    {
        sysTask.setUpdateTime(DateUtils.getNowDate());
        return sysTaskMapper.updateSysTask(sysTask);
    }

    /**
     * 批量删除Web端任务中心任务
     *
     * @param taskIds 需要删除的Web端任务中心任务主键
     * @return 结果
     */
    @Override
    public int deleteSysTaskByTaskIds(Long[] taskIds)
    {
        return sysTaskMapper.deleteSysTaskByTaskIds(taskIds);
    }

    /**
     * 删除Web端任务中心任务信息
     *
     * @param taskId Web端任务中心任务主键
     * @return 结果
     */
    @Override
    public int deleteSysTaskByTaskId(Long taskId)
    {
        return sysTaskMapper.deleteSysTaskByTaskId(taskId);
    }
}
