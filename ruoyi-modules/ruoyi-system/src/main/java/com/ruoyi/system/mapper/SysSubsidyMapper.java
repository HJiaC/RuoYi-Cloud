package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSubsidy;

/**
 * 补贴发放Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-20
 */
public interface SysSubsidyMapper
{
    /**
     * 查询补贴发放
     *
     * @param subsidyId 补贴发放主键
     * @return 补贴发放
     */
    public SysSubsidy selectSysSubsidyBySubsidyId(Long subsidyId);

    /**
     * 查询补贴发放列表
     *
     * @param sysSubsidy 补贴发放
     * @return 补贴发放集合
     */
    public List<SysSubsidy> selectSysSubsidyList(SysSubsidy sysSubsidy);

    /**
     * 新增补贴发放
     *
     * @param sysSubsidy 补贴发放
     * @return 结果
     */
    public int insertSysSubsidy(SysSubsidy sysSubsidy);

    /**
     * 修改补贴发放
     *
     * @param sysSubsidy 补贴发放
     * @return 结果
     */
    public int updateSysSubsidy(SysSubsidy sysSubsidy);

    /**
     * 删除补贴发放
     *
     * @param subsidyId 补贴发放主键
     * @return 结果
     */
    public int deleteSysSubsidyBySubsidyId(Long subsidyId);

    /**
     * 批量删除补贴发放
     *
     * @param subsidyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSubsidyBySubsidyIds(Long[] subsidyIds);
}
