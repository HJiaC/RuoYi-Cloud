package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysTask;

import java.util.List;

/**
 * @filename: ISysTaskService
 * @author: HXJ
 * @date: 2023/2/16 3:22 PM
 * @version: V1.0
 * @description: Web端任务中心任务Service接口
 */
public interface ISysTaskService {
    /**
     * 查询Web端任务中心任务
     *
     * @param taskId Web端任务中心任务主键
     * @return Web端任务中心任务
     */
    public SysTask selectSysTaskByTaskId(Long taskId);

    /**
     * 查询Web端任务中心任务列表
     *
     * @param sysTask Web端任务中心任务
     * @return Web端任务中心任务集合
     */
    public List<SysTask> selectSysTaskList(SysTask sysTask);

    /**
     * 新增Web端任务中心任务
     *
     * @param sysTask Web端任务中心任务
     * @return 结果
     */
    public int insertSysTask(SysTask sysTask);

    /**
     * 修改Web端任务中心任务
     *
     * @param sysTask Web端任务中心任务
     * @return 结果
     */
    public int updateSysTask(SysTask sysTask);

    /**
     * 批量删除Web端任务中心任务
     *
     * @param taskIds 需要删除的Web端任务中心任务主键集合
     * @return 结果
     */
    public int deleteSysTaskByTaskIds(Long[] taskIds);

    /**
     * 删除Web端任务中心任务信息
     *
     * @param taskId Web端任务中心任务主键
     * @return 结果
     */
    public int deleteSysTaskByTaskId(Long taskId);
}
