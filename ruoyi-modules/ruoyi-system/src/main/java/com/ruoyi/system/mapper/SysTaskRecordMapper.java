package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTaskRecord;

/**
 * @filename: SysTaskRecordMapper
 * @author: HXJ
 * @date: 2023/2/16 4:07 PM
 * @version: V1.0
 * @description: 任务记录Mapper接口
 */
public interface SysTaskRecordMapper
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
     * @param sysTaskRecord 任务记录
     * @return 结果
     */
    public int updateSysTaskRecord(SysTaskRecord sysTaskRecord);

    /**
     * 删除任务记录
     *
     * @param recordId 任务记录主键
     * @return 结果
     */
    public int deleteSysTaskRecordByRecordId(Long recordId);

    /**
     * 批量删除任务记录
     *
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTaskRecordByRecordIds(Long[] recordIds);
}
