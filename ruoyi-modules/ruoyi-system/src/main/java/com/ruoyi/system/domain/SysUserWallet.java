package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @filename: SysUserWallet
 * @author: HXJ
 * @date: 2023/2/20 3:24 PM
 * @version: V1.0
 * @description:
 */
public class SysUserWallet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 钱包ID
     */
    private Long walletId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 钱包当前状态
     0：可用
     1：冻结 */
//    @Excel(name = "钱包当前状态
//            0：可用
//            1：冻结")
            private Long status;
//
//            /** 钱包余额 */
//            @Excel(name = "钱包余额")
            private Long balance;

            public void setWalletId(Long walletId)
    {
        this.walletId = walletId;
    }

    public Long getWalletId()
    {
        return walletId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setBalance(Long balance)
    {
        this.balance = balance;
    }

    public Long getBalance()
    {
        return balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("walletId", getWalletId())
                .append("userId", getUserId())
                .append("status", getStatus())
                .append("balance", getBalance())
                .toString();
    }
}
