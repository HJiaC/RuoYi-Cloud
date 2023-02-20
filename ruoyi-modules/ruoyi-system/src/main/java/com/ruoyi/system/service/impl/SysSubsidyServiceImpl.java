package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSubsidyMapper;
import com.ruoyi.system.domain.SysSubsidy;
import com.ruoyi.system.service.ISysSubsidyService;

/**
 * 补贴发放Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-20
 */
@Service
public class SysSubsidyServiceImpl implements ISysSubsidyService
{
    @Autowired
    private SysSubsidyMapper sysSubsidyMapper;

    /**
     * 查询补贴发放
     *
     * @param subsidyId 补贴发放主键
     * @return 补贴发放
     */
    @Override
    public SysSubsidy selectSysSubsidyBySubsidyId(Long subsidyId)
    {
        return sysSubsidyMapper.selectSysSubsidyBySubsidyId(subsidyId);
    }

    /**
     * 查询补贴发放列表
     *
     * @param sysSubsidy 补贴发放
     * @return 补贴发放
     */
    @Override
    public List<SysSubsidy> selectSysSubsidyList(SysSubsidy sysSubsidy)
    {
        return sysSubsidyMapper.selectSysSubsidyList(sysSubsidy);
    }

    /**
     * 新增补贴发放
     *
     * @param sysSubsidy 补贴发放
     * @return 结果
     */
    @Override
    public int insertSysSubsidy(SysSubsidy sysSubsidy)
    {
        sysSubsidy.setCreateTime(DateUtils.getNowDate());
        return sysSubsidyMapper.insertSysSubsidy(sysSubsidy);
    }

    /**
     * 修改补贴发放
     *
     * @param sysSubsidy 补贴发放
     * @return 结果
     */
    @Override
    public int updateSysSubsidy(SysSubsidy sysSubsidy)
    {
        return sysSubsidyMapper.updateSysSubsidy(sysSubsidy);
    }

    /**
     * 批量删除补贴发放
     *
     * @param subsidyIds 需要删除的补贴发放主键
     * @return 结果
     */
    @Override
    public int deleteSysSubsidyBySubsidyIds(Long[] subsidyIds)
    {
        return sysSubsidyMapper.deleteSysSubsidyBySubsidyIds(subsidyIds);
    }

    /**
     * 删除补贴发放信息
     *
     * @param subsidyId 补贴发放主键
     * @return 结果
     */
    @Override
    public int deleteSysSubsidyBySubsidyId(Long subsidyId)
    {
        return sysSubsidyMapper.deleteSysSubsidyBySubsidyId(subsidyId);
    }
}
