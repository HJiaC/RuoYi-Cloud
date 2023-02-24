package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDispatchRecordMapper;
import com.ruoyi.system.domain.SysDispatchRecord;
import com.ruoyi.system.service.ISysDispatchRecordService;

/**
 * @filename: SysDispatchRecordServiceImpl
 * @author: HXJ
 * @date: 2023/2/23 1:59 PM
 * @version: V1.0
 * @description:
 */
@Service
public class SysDispatchRecordServiceImpl implements ISysDispatchRecordService
{
    @Autowired
    private SysDispatchRecordMapper sysDispatchRecordMapper;

    /**
     * 查询人员调度记录
     *
     * @param dispatchId 人员调度记录主键
     * @return 人员调度记录
     */
    @Override
    public SysDispatchRecord selectSysDispatchRecordByDispatchId(Long dispatchId)
    {
        return sysDispatchRecordMapper.selectSysDispatchRecordByDispatchId(dispatchId);
    }

    /**
     * 查询人员调度记录列表
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 人员调度记录
     */
    @Override
    public List<SysDispatchRecord> selectSysDispatchRecordList(SysDispatchRecord sysDispatchRecord)
    {
        return sysDispatchRecordMapper.selectSysDispatchRecordList(sysDispatchRecord);
    }

    /**
     * 新增人员调度记录
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 结果
     */
    @Override
    public int insertSysDispatchRecord(SysDispatchRecord sysDispatchRecord)
    {
        sysDispatchRecord.setCreateTime(DateUtils.getNowDate());
        return sysDispatchRecordMapper.insertSysDispatchRecord(sysDispatchRecord);
    }

    /**
     * 修改人员调度记录
     *
     * @param sysDispatchRecord 人员调度记录
     * @return 结果
     */
    @Override
    public int updateSysDispatchRecord(SysDispatchRecord sysDispatchRecord)
    {
        sysDispatchRecord.setUpdateTime(DateUtils.getNowDate());
        return sysDispatchRecordMapper.updateSysDispatchRecord(sysDispatchRecord);
    }

    /**
     * 批量删除人员调度记录
     *
     * @param dispatchIds 需要删除的人员调度记录主键
     * @return 结果
     */
    @Override
    public int deleteSysDispatchRecordByDispatchIds(Long[] dispatchIds)
    {
        return sysDispatchRecordMapper.deleteSysDispatchRecordByDispatchIds(dispatchIds);
    }

    /**
     * 删除人员调度记录信息
     *
     * @param dispatchId 人员调度记录主键
     * @return 结果
     */
    @Override
    public int deleteSysDispatchRecordByDispatchId(Long dispatchId)
    {
        return sysDispatchRecordMapper.deleteSysDispatchRecordByDispatchId(dispatchId);
    }
}
