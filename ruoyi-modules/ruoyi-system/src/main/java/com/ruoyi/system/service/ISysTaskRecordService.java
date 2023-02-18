package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysTaskRecord;
import com.ruoyi.system.domain.dto.SysTaskRecordOperation;

/**
 * @filename: ISysTaskRecordService
 * @author: HXJ
 * @date: 2023/2/16 4:04 PM
 * @version: V1.0
 * @description: 任务记录Service接口
 */
public interface ISysTaskRecordService
{
    /**
     * 查询任务记录
     *
     * @param recordId 任务记录主键
     * @return 任务记录
     */
    public SysTaskRecord selectSysTaskRecordByRecordId(Long recordId);

    /**
     * 查询任务记录列表
     *
     * @param sysTaskRecord 任务记录
     * @return 任务记录集合
     */
    public List<SysTaskRecord> selectSysTaskRecordList(SysTaskRecord sysTaskRecord);

    /**
     * 新增任务记录
     *
     * @param sysTaskRecord 任务记录
     * @return 结果
     */
    public int insertSysTaskRecord(SysTaskRecord sysTaskRecord);

    /**
     * 修改任务记录
     *
     * @param sysTaskRecordOperation 任务记录
     * @return 结果
     */
    public int updateSysTaskRecord(SysTaskRecordOperation sysTaskRecordOperation);

    /**
     * 批量删除任务记录
     *
     * @param recordIds 需要删除的任务记录主键集合
     * @return 结果
     */
    public int deleteSysTaskRecordByRecordIds(Long[] recordIds);

    /**
     * 删除任务记录信息
     *
     * @param recordId 任务记录主键
     * @return 结果
     */
    public int deleteSysTaskRecordByRecordId(Long recordId);
}
