package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysScheduleRecordMapper;
import com.ruoyi.system.domain.SysScheduleRecord;
import com.ruoyi.system.service.ISysScheduleRecordService;

/**
 * @filename: SysScheduleRecordServiceImpl
 * @author: HXJ
 * @date: 2023/2/16 4:06 PM
 * @version: V1.0
 * @description: 人员调度记录Service业务层处理
 */
@Service
public class SysScheduleRecordServiceImpl implements ISysScheduleRecordService
{
    @Autowired
    private SysScheduleRecordMapper sysScheduleRecordMapper;

    /**
     * 查询人员调度记录
     *
     * @param scheduleId 人员调度记录主键
     * @return 人员调度记录
     */
    @Override
    public SysScheduleRecord selectSysScheduleRecordByScheduleId(Long scheduleId)
    {
        return sysScheduleRecordMapper.selectSysScheduleRecordByScheduleId(scheduleId);
    }

    /**
     * 查询人员调度记录列表
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 人员调度记录
     */
    @Override
    public List<SysScheduleRecord> selectSysScheduleRecordList(SysScheduleRecord sysScheduleRecord)
    {
        return sysScheduleRecordMapper.selectSysScheduleRecordList(sysScheduleRecord);
    }

    /**
     * 新增人员调度记录
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 结果
     */
    @Override
    public int insertSysScheduleRecord(SysScheduleRecord sysScheduleRecord)
    {
        sysScheduleRecord.setCreateTime(DateUtils.getNowDate());
        return sysScheduleRecordMapper.insertSysScheduleRecord(sysScheduleRecord);
    }

    /**
     * 修改人员调度记录
     *
     * @param sysScheduleRecord 人员调度记录
     * @return 结果
     */
    @Override
    public int updateSysScheduleRecord(SysScheduleRecord sysScheduleRecord)
    {
        sysScheduleRecord.setUpdateTime(DateUtils.getNowDate());
        return sysScheduleRecordMapper.updateSysScheduleRecord(sysScheduleRecord);
    }

    /**
     * 批量删除人员调度记录
     *
     * @param scheduleIds 需要删除的人员调度记录主键
     * @return 结果
     */
    @Override
    public int deleteSysScheduleRecordByScheduleIds(Long[] scheduleIds)
    {
        return sysScheduleRecordMapper.deleteSysScheduleRecordByScheduleIds(scheduleIds);
    }

    /**
     * 删除人员调度记录信息
     *
     * @param scheduleId 人员调度记录主键
     * @return 结果
     */
    @Override
    public int deleteSysScheduleRecordByScheduleId(Long scheduleId)
    {
        return sysScheduleRecordMapper.deleteSysScheduleRecordByScheduleId(scheduleId);
    }
}
