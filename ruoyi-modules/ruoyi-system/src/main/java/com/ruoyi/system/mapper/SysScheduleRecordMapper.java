package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysScheduleRecord;

/**
 * @filename: SysScheduleRecordMapper
 * @author: HXJ
 * @date: 2023/2/16 3:59 PM
 * @version: V1.0
 * @description: 人员调度记录Mapper接口
 */
public interface SysScheduleRecordMapper
{
    /**
     * 查询人员调度记录
     *
     * @param scheduleId 人员调度记录主键
     * @return 人员调度记录
     */
    public SysScheduleRecord selectSysScheduleRecordByScheduleId(Long scheduleId);

    /**
     * 查询人员调度记录列表
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 人员调度记录集合
     */
    public List<SysScheduleRecord> selectSysScheduleRecordList(SysScheduleRecord sysScheduleRecord);

    /**
     * 新增人员调度记录
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 结果
     */
    public int insertSysScheduleRecord(SysScheduleRecord sysScheduleRecord);

    /**
     * 修改人员调度记录
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 结果
     */
    public int updateSysScheduleRecord(SysScheduleRecord sysScheduleRecord);

    /**
     * 删除人员调度记录
     *
     * @param scheduleId 人员调度记录主键
     * @return 结果
     */
    public int deleteSysScheduleRecordByScheduleId(Long scheduleId);

    /**
     * 批量删除人员调度记录
     *
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysScheduleRecordByScheduleIds(Long[] scheduleIds);
}
