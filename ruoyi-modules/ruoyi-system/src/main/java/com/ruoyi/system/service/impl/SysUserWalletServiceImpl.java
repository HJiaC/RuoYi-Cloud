package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserWalletMapper;
import com.ruoyi.system.domain.SysUserWallet;
import com.ruoyi.system.service.ISysUserWalletService;

/**
 * @filename: SysUserWalletServiceImpl
 * @author: HXJ
 * @date: 2023/2/20 3:25 PM
 * @version: V1.0
 * @description:
 */
@Service
public class SysUserWalletServiceImpl implements ISysUserWalletService
{
    @Autowired
    private SysUserWalletMapper sysUserWalletMapper;

    /**
     * 查询用户钱包
     *
     * @param walletId 用户钱包主键
     * @return 用户钱包
     */
    @Override
    public SysUserWallet selectSysUserWalletByWalletId(Long walletId)
    {
        return sysUserWalletMapper.selectSysUserWalletByWalletId(walletId);
    }

    /**
     * 查询用户钱包列表
     *
     * @param sysUserWallet 用户钱包
     * @return 用户钱包
     */
    @Override
    public List<SysUserWallet> selectSysUserWalletList(SysUserWallet sysUserWallet)
    {
        return sysUserWalletMapper.selectSysUserWalletList(sysUserWallet);
    }

    /**
     * 新增用户钱包
     *
     * @param sysUserWallet 用户钱包
     * @return 结果
     */
    @Override
    public int insertSysUserWallet(SysUserWallet sysUserWallet)
    {
        return sysUserWalletMapper.insertSysUserWallet(sysUserWallet);
    }

    /**
     * 修改用户钱包
     *
     * @param sysUserWallet 用户钱包
     * @return 结果
     */
    @Override
    public int updateSysUserWallet(SysUserWallet sysUserWallet)
    {
        return sysUserWalletMapper.updateSysUserWallet(sysUserWallet);
    }

    /**
     * 批量删除用户钱包
     *
     * @param walletIds 需要删除的用户钱包主键
     * @return 结果
     */
    @Override
    public int deleteSysUserWalletByWalletIds(Long[] walletIds)
    {
        return sysUserWalletMapper.deleteSysUserWalletByWalletIds(walletIds);
    }

    /**
     * 删除用户钱包信息
     *
     * @param walletId 用户钱包主键
     * @return 结果
     */
    @Override
    public int deleteSysUserWalletByWalletId(Long walletId)
    {
        return sysUserWalletMapper.deleteSysUserWalletByWalletId(walletId);
    }
}
