package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysDispatchRecord;

/**
 * @filename: SysDispatchRecordMapper
 * @author: HXJ
 * @date: 2023/2/23 2:00 PM
 * @version: V1.0
 * @description:
 */


public interface SysDispatchRecordMapper
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
     * 删除人员调度记录
     *
     * @param dispatchId 人员调度记录主键
     * @return 结果
     */
    public int deleteSysDispatchRecordByDispatchId(Long dispatchId);

    /**
     * 批量删除人员调度记录
     *
     * @param dispatchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDispatchRecordByDispatchIds(Long[] dispatchIds);
}
