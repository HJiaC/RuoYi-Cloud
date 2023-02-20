package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysUserWallet;

/**
 * @filename: ISysUserWalletService
 * @author: HXJ
 * @date: 2023/2/20 3:23 PM
 * @version: V1.0
 * @description:
 */
public interface ISysUserWalletService
{
    /**
     * 查询用户钱包
     *
     * @param walletId 用户钱包主键
     * @return 用户钱包
     */
    public SysUserWallet selectSysUserWalletByWalletId(Long walletId);

    /**
     * 查询用户钱包列表
     *
     * @param sysUserWallet 用户钱包
     * @return 用户钱包集合
     */
    public List<SysUserWallet> selectSysUserWalletList(SysUserWallet sysUserWallet);

    /**
     * 新增用户钱包
     *
     * @param sysUserWallet 用户钱包
     * @return 结果
     */
    public int insertSysUserWallet(SysUserWallet sysUserWallet);

    /**
     * 修改用户钱包
     *
     * @param sysUserWallet 用户钱包
     * @return 结果
     */
    public int updateSysUserWallet(SysUserWallet sysUserWallet);

    /**
     * 批量删除用户钱包
     *
     * @param walletIds 需要删除的用户钱包主键集合
     * @return 结果
     */
    public int deleteSysUserWalletByWalletIds(Long[] walletIds);

    /**
     * 删除用户钱包信息
     *
     * @param walletId 用户钱包主键
     * @return 结果
     */
    public int deleteSysUserWalletByWalletId(Long walletId);
}
