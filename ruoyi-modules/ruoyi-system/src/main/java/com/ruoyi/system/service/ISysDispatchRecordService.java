package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDispatchRecord;

import java.util.List;

/**
 * @filename: ISysDispatchRecordService
 * @author: HXJ
 * @date: 2023/2/23 1:58 PM
 * @version: V1.0
 * @description: 人员调度记录Service接口
 */
public interface ISysDispatchRecordService
{
    /**
     * 查询人员调度记录
     *
     * @param dispatchId 人员调度记录主键
     * @return 人员调度记录
     */
    public SysDispatchRecord selectSysDispatchRecordByDispatchId(Long dispatchId);

    /**
     * 查询人员调度记录列表
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 人员调度记录集合
     */
    public List<SysDispatchRecord> selectSysDispatchRecordList(SysDispatchRecord sysDispatchRecord);

    /**
     * 新增人员调度记录
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 结果
     */
    public int insertSysDispatchRecord(SysDispatchRecord sysDispatchRecord);

    /**
     * 修改人员调度记录
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 结果
     */
    public int updateSysDispatchRecord(SysDispatchRecord sysDispatchRecord);

    /**
     * 批量删除人员调度记录
     *
     * @param dispatchIds 需要删除的人员调度记录主键集合
     * @return 结果
     */
    public int deleteSysDispatchRecordByDispatchIds(Long[] dispatchIds);

    /**
     * 删除人员调度记录信息
     *
     * @param dispatchId 人员调度记录主键
     * @return 结果
     */
    public int deleteSysDispatchRecordByDispatchId(Long dispatchId);
}
