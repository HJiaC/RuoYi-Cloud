package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.dto.SysTaskRecordOperation;
import com.ruoyi.system.domain.vo.TaskRecordStatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTaskRecordMapper;
import com.ruoyi.system.domain.SysTaskRecord;
import com.ruoyi.system.service.ISysTaskRecordService;

/**
 * @filename: SysTaskRecordServiceImpl
 * @author: HXJ
 * @date: 2023/2/16 4:07 PM
 * @version: V1.0
 * @description: 任务记录Service业务层处理
 */
@Service
public class SysTaskRecordServiceImpl implements ISysTaskRecordService
{
    @Autowired
    private SysTaskRecordMapper sysTaskRecordMapper;

    /**
     * 查询任务记录
     *
     * @param recordId 任务记录主键
     * @return 任务记录
     */
    @Override
    public SysTaskRecord selectSysTaskRecordByRecordId(Long recordId)
    {
        return sysTaskRecordMapper.selectSysTaskRecordByRecordId(recordId);
    }

    /**
     * 查询任务记录列表
     *
     * @param sysTaskRecord 任务记录
     * @return 任务记录
     */
    @Override
    public List<SysTaskRecord> selectSysTaskRecordList(SysTaskRecord sysTaskRecord)
    {
        return sysTaskRecordMapper.selectSysTaskRecordList(sysTaskRecord);
    }

    /**
     * 新增任务记录
     *
     * @param sysTaskRecord 任务记录
     * @return 结果
     */
    @Override
    public int insertSysTaskRecord(SysTaskRecord sysTaskRecord)
    {
        return sysTaskRecordMapper.insertSysTaskRecord(sysTaskRecord);
    }

    /**
     * 修改任务记录
     *
     * @param sysTaskRecordOperation 任务记录
     * @return 结果
     */
    @Override
    public int updateSysTaskRecord(SysTaskRecordOperation sysTaskRecordOperation)
    {
        return sysTaskRecordMapper.updateSysTaskRecord(sysTaskRecordOperation);
    }

    /**
     * 批量删除任务记录
     *
     * @param recordIds 需要删除的任务记录主键
     * @return 结果
     */
    @Override
    public int deleteSysTaskRecordByRecordIds(Long[] recordIds)
    {
        return sysTaskRecordMapper.deleteSysTaskRecordByRecordIds(recordIds);
    }

    /**
     * 删除任务记录信息
     *
     * @param recordId 任务记录主键
     * @return 结果
     */
    @Override
    public int deleteSysTaskRecordByRecordId(Long recordId)
    {
        return sysTaskRecordMapper.deleteSysTaskRecordByRecordId(recordId);
    }

    /**
     * 获取考勤中心统计信息
     *
     * @return list
     */
    @Override
    public TaskRecordStatisticVo getTaskRecordStatistic() {
        TaskRecordStatisticVo statisticVo = new TaskRecordStatisticVo();
        statisticVo.setMemberNum(sysTaskRecordMapper.getMemberNum());
        statisticVo.setExceptionNum(sysTaskRecordMapper.getExceptionNum());
        statisticVo.setRecordNum(sysTaskRecordMapper.getTaskRecordNum());
        statisticVo.setAttendanceRate(90.3D);
        return statisticVo;
    }
}
