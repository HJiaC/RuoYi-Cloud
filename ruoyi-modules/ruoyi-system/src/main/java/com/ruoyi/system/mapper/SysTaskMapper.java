package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTask;

/**
 * @filename: SysTaskMapper
 * @author: HXJ
 * @date: 2023/2/16 3:26 PM
 * @version: V1.0
 * @description: Web端任务中心任务Mapper接口
 */
public interface SysTaskMapper
{
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
     * 删除Web端任务中心任务
     *
     * @param taskId Web端任务中心任务主键
     * @return 结果
     */
    public int deleteSysTaskByTaskId(Long taskId);

    /**
     * 批量删除Web端任务中心任务
     *
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTaskByTaskIds(Long[] taskIds);
}
